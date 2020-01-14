package com.mcbanners.gameserverapi.game.service;

import com.mcbanners.gameserverapi.game.status.GameStatus;

public abstract class GameService {
    private int port;

    public GameService(int port) {
        this.port = port;
    }

    /**
     * Get the status of the {@link com.mcbanners.gameserverapi.game.GameType} server
     * on the default port.
     * @param hostname the hostname of the game server
     * @return the {@link GameStatus}
     */
    public GameStatus getStatus(String hostname) {
        return getStatus(hostname, port);
    }

    /**
     * Get the status of the {@link com.mcbanners.gameserverapi.game.GameType} server
     * on the given port.
     * @param hostname the hostname of the game server
     * @param port the port of the game server
     * @return the {@link GameStatus}
     */
    public abstract GameStatus getStatus(String hostname, int port);

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
