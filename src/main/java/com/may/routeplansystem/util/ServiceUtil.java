package com.may.routeplansystem.util;

import com.may.routeplansystem.constant.ExceptionMessage;
import com.may.routeplansystem.exception.SqlExecutedException;

public class ServiceUtil {

    public static void checkSqlExecuted(boolean flag) {
        if (!flag) {
            throw new SqlExecutedException(ExceptionMessage.MYSQL_EXECUTED_FAIL);
        }
    }
}
