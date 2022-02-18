package com.mcbanners.mcapi.model;

public class Motd {
    private String raw;
    private String colorless;
    private String formatted;

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getFormatted() {
        return formatted;
    }

    public void setFormatted(String formatted) {
        this.formatted = formatted;
    }

    public String getColorless() {
        return colorless;
    }

    public void setColorless(String colorless) {
        this.colorless = colorless;
    }
}
