package com.vertx.sample.infrastructure;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import static com.vertx.sample.infrastructure.HttpConstants.APPLICATION_JSON;
import static com.vertx.sample.infrastructure.HttpConstants.CONTENT_TYPE;


public class ErrorResponse {

    public static void respondInternalServerError(RoutingContext routingContext, String message) {
        respond(routingContext, message, 500);
    }

    private static void respond(RoutingContext routingContext, String message, int httpCode) {
        HttpServerResponse response = routingContext.response();

        JsonObject jsonResponse = new JsonObject().put("message", message);

        response.setStatusCode(httpCode);
        response.putHeader(CONTENT_TYPE, APPLICATION_JSON);
        response.end(jsonResponse.toString());
    }
}
