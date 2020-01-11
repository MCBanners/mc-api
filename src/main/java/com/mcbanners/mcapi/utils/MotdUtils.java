package com.mcbanners.mcapi.utils;

import java.util.regex.Pattern;

public class MotdUtils {
    private static final Pattern COLOR = Pattern.compile("(?i)\u00A7[0-9A-FK-OR]");

    /**
     * Strips superfluous formatting from the MOTD
     * @param input the unformatted MOTD description
     * @return the formatted MOTD description
     */
    public static String clean(final String input) {
        return COLOR.matcher(input)
                .replaceAll("")
                .replaceAll("[\\r\\n]+", " ")
                .replaceAll("\\s\\s+", " ");
    }
}
