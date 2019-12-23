package com.mcbanners.mcapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServerStatus {
    private String host;
    private int port;
    private String version;
    private PlayersInfo players = new PlayersInfo();

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public PlayersInfo getPlayers() {
        return players;
    }

    public void setPlayers(PlayersInfo players) {
        this.players = players;
    }
}
