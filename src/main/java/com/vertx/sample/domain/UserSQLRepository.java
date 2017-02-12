package com.vertx.sample.domain;

import io.vertx.ext.asyncsql.AsyncSQLClient;

import java.util.concurrent.CompletableFuture;

public class UserSQLRepository {
    private final AsyncSQLClient postgreSQLClient;

    public UserSQLRepository(AsyncSQLClient postgreSQLClient) {
        this.postgreSQLClient = postgreSQLClient;
    }

    public CompletableFuture<String> addUser() {
        CompletableFuture<String> responseFuture = new CompletableFuture<>();
        postgreSQLClient.getConnection(connectionRes -> {
            throw new RuntimeException("Some exception!");
        });
        return responseFuture;
    }
}
