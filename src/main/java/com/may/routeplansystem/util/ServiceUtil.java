package com.may.routeplansystem.util;

public class ServiceUtil {

    public static void checkSqlExecuted(boolean flag, String msg) {
        if (!flag) {
            throw new SqlExecuteException()
        }
    }

    public static class SqlExecuteException extends RuntimeException {

        public SqlExecuteException(String msg) {
            super(msg);
        }
    }
}
