package com.vertx.sample;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class VertxSampleApplication {

    private static void start() {
        VertxOptions options = new VertxOptions().setClustered(false);
        Vertx vertx = Vertx.vertx(options);

        try {
            vertx.deployVerticle(new VertxSampleServer(), new DeploymentOptions());
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }

        // Here we are waiting for uncaught exception
        vertx.exceptionHandler(e -> {
           System.out.println("Point which is never executed, but it should be. Why?");
        });
    }

    public static void main(String[] args) {
        start();
    }

}
