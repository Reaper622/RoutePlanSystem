package com.may.routeplansystem.util;

public class ServiceUtil {

    public static void checkSqlExecuted(boolean flag){
        if (!flag){
          throw new SqlExecuteException("服务器错误");
          }
    }

public static class SqlExecuteException extends RuntimeException{

    public SqlExecuteException(String msg){
        super(msg);
    }
}
}
