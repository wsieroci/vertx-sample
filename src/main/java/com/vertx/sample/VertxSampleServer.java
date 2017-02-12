package com.vertx.sample;

import com.vertx.sample.infrastructure.PostgresConnectionPool;
import com.vertx.sample.web.UserController;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

import static java.lang.String.format;

public class VertxSampleServer extends AbstractVerticle {

    public static final int PORT = 7071;

    private final Handler<AsyncResult<HttpServer>> listenSuccessHandler;

    public VertxSampleServer() {
        super();
        listenSuccessHandler = null;
    }

    @Override
    public void start() throws Exception {
        PostgresConnectionPool postgresConnectionPool = new PostgresConnectionPool(vertx);

        Router mainRouter = Router.router(vertx);
        mainRouter.mountSubRouter("/api", new UserController(vertx, postgresConnectionPool.getPostgreSQLClient()).createRouter());

        vertx.createHttpServer()
             .requestHandler(mainRouter::accept)
             .listen(PORT, handler -> {
                 if (handler.succeeded()) {
                     System.out.println(format("Server is running on port %s", PORT));
                     if (listenSuccessHandler != null) {
                         listenSuccessHandler.handle(handler);
                     }
                 } else {
                     System.err.println(format("Failed to listen on port %s", PORT));
                 }
             });
    }
}
