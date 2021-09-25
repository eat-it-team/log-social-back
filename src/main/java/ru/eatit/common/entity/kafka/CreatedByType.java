package ru.eatit.common.entity.kafka;

/**
 *
 */
public class CreatedByType {

    private static class Prefix {
        private static final String DB = "db_";
        private static final String USER = "user_";
        private static final String EXTERNAL_SERVICE = "external_";

    }

    public static String getDbServiceName(String dbName) {
        return String.format("%s%s", Prefix.DB, dbName);
    }
}
