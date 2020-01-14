package com.mcbanners.gameserverapi.game.status.minecraft;

public class PlayerInfo {
    private int online;
    private int max;

    public int getOnline() {
        return online;
    }

    public PlayerInfo setOnline(int online) {
        this.online = online;
        return this;
    }

    public int getMax() {
        return max;
    }

    public PlayerInfo setMax(int max) {
        this.max = max;
        return this;
    }
}
