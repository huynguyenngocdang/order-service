package com.huynguyenngocdang.order_service.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringWorkerUtils {
    public static String trimLowerCase(String str) {
        return str.trim().toLowerCase();
    }

    public static String likeSqlTrimLowerCase(String str) {
        return "%" + trimLowerCase(str) + "%";
    }
}
