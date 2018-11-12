package com.may.routeplansystem.util;

public class ServiceUtil {

    public static void checkSqlExecuted(boolean flag){
        if (!flag){
          throw new SalExecuteException("服务器错误");
          }
    }

public static class SalExecuteException extends RuntimeException{

    public SalExecuteException(String msg){
        super(msg);
    }
}
}
