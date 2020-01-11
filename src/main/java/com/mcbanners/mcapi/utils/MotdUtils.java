package com.mcbanners.mcapi.utils;

public class MotdUtils {
    /**
     * Strips superfluous formatting from the MOTD
     * @param input the unformatted MOTD description
     * @return the formatted MOTD description
     */
    public static String clean(final String input) {
        return input
                // remove all MC color codes
                .replaceAll("(?i)\u00A7[0-9A-FK-OR](?-i)", "")
                // remove all non-ASCII (superfluous)
                .replaceAll("[^\\x00-\\x7F]", "")
                // remove all newlines (sorry)
                .replaceAll("[\\r\\n]+", " ")
                // remove extra spaces (superfluous)
                .replaceAll("\\s\\s+", " ")
                // trim leading and trailing spaces (superfluous)
                .trim();
    }
}
