package com.vertx.sample.infrastructure;

import com.vertx.sample.config.ApplicationConfiguration;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.asyncsql.PostgreSQLClient;

public class PostgresConnectionPool {
    private final AsyncSQLClient postgreSQLClient;

    public PostgresConnectionPool(Vertx vertx) {
        JsonObject postgreSQLClientConfig = new JsonObject();
        postgreSQLClientConfig.put("host", ApplicationConfiguration.getDatabaseHost());
        postgreSQLClientConfig.put("port", ApplicationConfiguration.getDatabasePort());
        postgreSQLClientConfig.put("username", ApplicationConfiguration.getDatabaseUsername());
        postgreSQLClientConfig.put("password", ApplicationConfiguration.getDatabasePassword());
        postgreSQLClientConfig.put("database", ApplicationConfiguration.getDatabaseName());
        postgreSQLClientConfig.put("maxPoolSize", ApplicationConfiguration.getDatabasePoolSize());
        postgreSQLClient = PostgreSQLClient.createShared(vertx, postgreSQLClientConfig);
    }

    public AsyncSQLClient getPostgreSQLClient() {
        return postgreSQLClient;
    }
}
