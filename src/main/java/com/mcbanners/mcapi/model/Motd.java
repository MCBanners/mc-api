package com.mcbanners.mcapi.model;

public class Motd {
    private String raw;
    private String stripped;
    private String[] formatted;

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getStripped() {
        return stripped;
    }

    public void setStripped(String stripped) {
        this.stripped = stripped;
    }

    public String[] getFormatted() {
        return formatted;
    }

    public void setFormatted(String[] formatted) {
        this.formatted = formatted;
    }
}
