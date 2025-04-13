package com.library.demo;

import org.jdbi.v3.core.config.ConfigRegistry;
import org.jdbi.v3.core.mapper.ColumnMapper;
import org.jdbi.v3.core.mapper.ColumnMapperFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class BaseUUIDColumnMapperFactory implements ColumnMapperFactory {
    @Override
    public Optional<ColumnMapper<?>> build(Type type, ConfigRegistry config) {

        //checks that the requested type is a Class
        if (!(type instanceof Class<?> clazz)) {
            return Optional.empty();
        }

        //Checks that the class extends our BaseUUID abstract class
        if (!BaseUUID.class.isAssignableFrom(clazz)) {
            return Optional.empty();
        }

        //Map the column
        return Optional.of((rs, columnNumber, ctx) -> {
            try {
               var uuid = extractRawUUIDFromColumn(rs, columnNumber);

                if (uuid == null) {
                    return null;
                }

                return wrapNativeUUID(clazz, uuid);
            } catch (Exception e) {
                throw new SQLException("Could not map column " + columnNumber + " to " + clazz.getSimpleName() + ": " + e.getMessage(), e);
            }
        });
    }

    private static Object wrapNativeUUID(Class<?> clazz, UUID uuid) throws
            InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        try {
            // First try the UUID constructor
            Constructor<?> constructor = clazz.getDeclaredConstructor(UUID.class);
            constructor.setAccessible(true);
            return constructor.newInstance(uuid);
        } catch (NoSuchMethodException e) {
            // Fall back to String constructor if UUID constructor isn't available
            Constructor<?> constructor = clazz.getDeclaredConstructor(String.class);
            constructor.setAccessible(true);
            return constructor.newInstance(uuid.toString());
        }
    }


    private UUID extractRawUUIDFromColumn(ResultSet rs, int columnNumber) throws SQLException {
        // First attempt to read as a native UUID object
        UUID uuid = null;
        try {
            // Try to get as a native UUID
            uuid = rs.getObject(columnNumber, UUID.class);
        } catch (SQLException | UnsupportedOperationException e) {
            // If that fails, try with a string
            String idValue = rs.getString(columnNumber);
            if (idValue != null) {
                try {
                    uuid = UUID.fromString(idValue);
                } catch (IllegalArgumentException ex) {
                    throw new SQLException("Invalid UUID format: " + idValue, ex);
                }
            }
        }
        return uuid;
    }

}
