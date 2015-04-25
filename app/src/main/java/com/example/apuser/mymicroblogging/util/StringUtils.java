package com.example.apuser.mymicroblogging.util;

/**
 * Created by mikelee on 4/25/15.
 */
public class StringUtils {
    private static final String EMPTY_STRING = "";

    private StringUtils() {
        //Empty
    }

    public static boolean isNullOrEmpty(final String string) {
        return string == null || EMPTY_STRING.equals(string);
    }
}
