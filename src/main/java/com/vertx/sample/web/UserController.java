package com.vertx.sample.web;

import com.vertx.sample.domain.UserSQLRepository;
import com.vertx.sample.infrastructure.ErrorResponse;
import com.vertx.sample.infrastructure.SuccessResponse;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class UserController {

    private final Vertx vertx;
    private final AsyncSQLClient postgreSQLClient;

    public UserController(Vertx vertx, AsyncSQLClient postgreSQLClient) {
        this.vertx = vertx;
        this.postgreSQLClient = postgreSQLClient;
    }

    public Router createRouter() {
        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());

        router.route(HttpMethod.POST, "/add-user").handler(routingContext -> addUser(routingContext));

        return router;
    }

    private void addUser(RoutingContext routingContext) {
        UserSQLRepository userSQLRepository = new UserSQLRepository(postgreSQLClient);

        userSQLRepository.addUser().thenAccept(messageResult -> {
            SuccessResponse successResponse = new SuccessResponse();
            JsonObject responseJson = new JsonObject();
            responseJson.put("message", messageResult);
            successResponse.respondOk(routingContext, responseJson);
        }).exceptionally(completableException -> {
            Throwable cause = completableException.getCause();
            ErrorResponse.respondInternalServerError(routingContext, cause.getMessage());
            return null;
        });
    }
}
