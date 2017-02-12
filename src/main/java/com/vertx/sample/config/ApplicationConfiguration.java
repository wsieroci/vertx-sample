package com.vertx.sample.config;

public class ApplicationConfiguration {

    private static final String DATABASE_DEFAULT_HOST = "localhost";
    private static final int DATABASE_DEFAULT_PORT = 6548;
    private static final String DATABASE_DEFAULT_NAME = "postgres";
    private static final String DATABASE_DEFAULT_USERNAME = "postgres";
    private static final String DATABASE_DEFAULT_PASSWORD = "";
    private static final int DATABASE_DEFAULT_POOL_SIZE = 5;
    private static final String TESTS_PROFILE = "tests";

    private static String readEnvironmentVariable(String variableName, String defaultValue) {
        String value = System.getenv(variableName);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    private static int readEnvironmentVariable(String variableName, int defaultValue) {
        String value = System.getenv(variableName);
        if (value == null) {
            return defaultValue;
        }
        return Integer.parseInt(value);
    }

    public static boolean isTestsProfile() {
        String profile = System.getenv("SERVICE_PROFILE");
        return profile != null && profile.equals(TESTS_PROFILE);
    }

    public static String getDatabaseHost() {
        return readEnvironmentVariable("SERVICE_DATABASE_HOST", DATABASE_DEFAULT_HOST);
    }

    public static int getDatabasePort() {
        return readEnvironmentVariable("SERVICE_DATABASE_PORT", DATABASE_DEFAULT_PORT);
    }

    public static String getDatabaseName() {
        return readEnvironmentVariable("SERVICE_DATABASE_NAME", DATABASE_DEFAULT_NAME);
    }

    public static String getDatabaseUsername() {
        return readEnvironmentVariable("SERVICE_DATABASE_USERNAME", DATABASE_DEFAULT_USERNAME);
    }

    public static String getDatabasePassword() {
        return readEnvironmentVariable("SERVICE_DATABASE_PASSWORD", DATABASE_DEFAULT_PASSWORD);
    }

    public static int getDatabasePoolSize() {
        return readEnvironmentVariable("SERVICE_DATABASE_POOL_SIZE", DATABASE_DEFAULT_POOL_SIZE);
    }
}
