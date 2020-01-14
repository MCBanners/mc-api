package com.mcbanners.gameserverapi.game.status.minecraft;

public class MessageOfTheDay {
    private String raw;
    private String formatted;

    public String getRaw() {
        return raw;
    }

    public MessageOfTheDay setRaw(String raw) {
        this.raw = raw;
        return this;
    }

    public String getFormatted() {
        return formatted;
    }

    public MessageOfTheDay setFormatted(String formatted) {
        this.formatted = formatted;
        return this;
    }

    public MessageOfTheDay autoformat() {
        return setFormatted(format(getRaw()));
    }

    public static String format(String input) {
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
