package com.mcbanners.mcapi.utils;

import java.util.regex.Pattern;

public class MotdUtils {

    public static final char COLOR_CHAR = '\u00A7';
    private static final Pattern STRIP = Pattern.compile("(?i)" + COLOR_CHAR + "[0-9A-FK-OR]");

    public static String stripColors(final String input) {
        return STRIP.matcher(input).replaceAll("");
    }
}
