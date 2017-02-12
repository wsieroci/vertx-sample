package com.vertx.sample.infrastructure;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import static com.vertx.sample.infrastructure.HttpConstants.APPLICATION_JSON;
import static com.vertx.sample.infrastructure.HttpConstants.CONTENT_TYPE;

public class SuccessResponse {
    public void respondOk(RoutingContext routingContext, JsonObject jsonBodyResponse) {
        HttpServerResponse response = routingContext.response();
        response.putHeader(CONTENT_TYPE, APPLICATION_JSON);
        response.end(jsonBodyResponse.toString());
    }
}
