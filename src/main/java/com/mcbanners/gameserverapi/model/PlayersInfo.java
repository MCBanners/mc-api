package com.mcbanners.gameserverapi.model;

public class PlayersInfo {
    private int online;
    private int max;

    public void setOnline(int online) {
        this.online = online;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getOnline() {
        return online;
    }

    public int getMax() {
        return max;
    }
}
