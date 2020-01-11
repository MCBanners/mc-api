package com.mcbanners.mcapi.utils;

import java.util.regex.Pattern;

public class MotdUtils {
    private static final char COLOR_CHAR = '\u00A7';
    private static final Pattern STRIP = Pattern.compile(String.format("(?i)%s[0-9A-FK-OR]", COLOR_CHAR));

    public static String stripColors(final String input) {
        return STRIP.matcher(input).replaceAll("");
    }

    public static String[] splitNewLines(final String input) {
        return input.split("[\\r\\n]+");
    }
}
