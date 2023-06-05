package com.example.mallproject.common.utils;

import com.example.mallproject.entity.User;

/**
 * @author zzzk1
 */
public final class ValidatorUtils {
    private ValidatorUtils(){}

    private static final String IS_EMPTY = "%s不能为空";
    private static final String LESS_THAN_ZERO = "%s不能小于0";
    private static final String IS_LOGIN = "未登录";
    public static void checkNull(Object param, String fieldName) {
        if (param == null) {
            throw new RuntimeException(String.format(IS_EMPTY, fieldName));
        }
    }

    public static void checkLogin(User user) {
        if (user == null) {
            throw new RuntimeException(IS_LOGIN);
        }
    }
}
