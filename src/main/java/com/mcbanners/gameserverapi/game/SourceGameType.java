package com.mcbanners.gameserverapi.game;

public enum SourceGameType {
    RUST(28015),
    CSGO(27015),
    SD2D(26900),
    ARK(27015);

    private final int port;

    SourceGameType(final int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }
}
