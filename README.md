Sample Vert.x application connecting to PostgreSQL
=============

Intention
------------------------------------

Done for purposes of checking how uncaught exceptions works in Vert.x
related to topic which I have created on `Vert.x Google Group`:

https://groups.google.com/forum/#!topic/vertx/b2dwadbLe1A

Server
------------------------------------

Server is listening on port 7071

Endpoint
------------------------------------

Its only endpoint is `/api/add-user` [POST]

Making requests to it shows how `Vert.x` is not giving us a way of handling
exceptions which are being thrown inside `SQL Context`

We are trying to catch exceptions by:

`vertx.exceptionHandler(e -> {
    System.out.println("Point which is never executed, but it should be. Why?");
});`

Database
------------------------------------

By default application is connecting to database using such parameters:
* DATABASE_DEFAULT_HOST = "localhost"
* DATABASE_DEFAULT_PORT = 6548
* DATABASE_DEFAULT_NAME = "postgres"
* DATABASE_DEFAULT_USERNAME = "postgres"
* DATABASE_DEFAULT_PASSWORD = ""
* DATABASE_DEFAULT_POOL_SIZE = 5