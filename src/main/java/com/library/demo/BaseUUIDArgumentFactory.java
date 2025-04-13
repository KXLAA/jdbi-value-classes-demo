package com.library.demo;

import org.jdbi.v3.core.argument.AbstractArgumentFactory;
import org.jdbi.v3.core.argument.Argument;
import org.jdbi.v3.core.config.ConfigRegistry;

import java.sql.Types;

//https://jdbi.org/releases/3.31.0/#_argumentfactory
public class BaseUUIDArgumentFactory extends AbstractArgumentFactory<BaseUUID> {
    public BaseUUIDArgumentFactory() {
        super(Types.OTHER);
    }

    @Override
    protected Argument build(BaseUUID value, ConfigRegistry config) {
        return (position, statement, ctx) -> {
            statement.setObject(position, value.getValue());
        };
    }
}
