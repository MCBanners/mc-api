package com.mcbanners.gameserverapi.game.status.minecraft;

import com.mcbanners.gameserverapi.game.status.GameStatus;

public class MinecraftGameStatus extends GameStatus {
    private String version;
    private PlayerInfo players;
    private MessageOfTheDay motd;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public PlayerInfo getPlayers() {
        return players;
    }

    public void setPlayers(PlayerInfo players) {
        this.players = players;
    }

    public MessageOfTheDay getMotd() {
        return motd;
    }

    public void setMotd(MessageOfTheDay motd) {
        this.motd = motd;
    }
}
