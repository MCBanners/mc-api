package com.mcbanners.mcapi.model;

public class ServerStatus {
    private String host;
    private int port;
    private String version;
    private PlayersInfo players = new PlayersInfo();
    private String motd;
    private String icon;

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public PlayersInfo getPlayers() {
        return players;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setMotd(String motd) {
        this.motd = motd;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getVersion() {
        return version;
    }

    public String getMotd() {
        return motd;
    }

    public String getIcon() {
        return icon;
    }
}
