package com.library.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.statement.SqlLogger;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.spring.SpringConnectionFactory;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import org.jdbi.v3.core.Jdbi;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JdbiConfig {

    @Bean
    public Jdbi jdbi(DataSource ds) {
        var cf = new SpringConnectionFactory(ds);
        var jdbi = Jdbi.create(cf);
        jdbi.installPlugin(new SqlObjectPlugin());
        jdbi.setSqlLogger(new Logger());

        jdbi.registerArgument(new BaseUUIDArgumentFactory());
        jdbi.registerColumnMapper(new BaseUUIDColumnMapperFactory());
        return jdbi;
    }

    public static class Logger implements SqlLogger {
        @Override
        public void logAfterExecution(StatementContext context) {
            log.info("Executed SQL -> {}, parameters {}, timeTaken {} ms",
                    context.getRenderedSql(),
                    context.getBinding().toString(),
                    context.getElapsedTime(ChronoUnit.MILLIS)
            );
        }
        @Override
        public void logException(StatementContext context, SQLException ex) {
            log.error("Exception executing SQL -> {}", context.getRenderedSql(), ex);
        }
    }
}
