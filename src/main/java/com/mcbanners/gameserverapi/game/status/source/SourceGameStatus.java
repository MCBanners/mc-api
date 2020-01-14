package com.mcbanners.gameserverapi.game.status.source;

import com.ibasco.agql.protocols.valve.source.query.pojos.SourcePlayer;
import com.mcbanners.gameserverapi.game.status.GameStatus;

import java.util.List;
import java.util.Map;

public class SourceGameStatus extends GameStatus {
    private String name;
    private byte networkVersion;
    private String mapName;
    private String gameDirectory;
    private String gameDescription;
    private short appId;
    private byte numOfPlayers;
    private byte maxPlayers;
    private byte numOfBots;
    private List<SourcePlayer> players;
    private boolean dedicated;
    private char operatingSystem;
    private boolean passwordProtected;
    private boolean secure;
    private String gameVersion;
    private long serverId;
    private short tvPort;
    private String tvName;
    private String serverTags;
    private Map<String, String> rules;
    private long gameId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getNetworkVersion() {
        return networkVersion;
    }

    public void setNetworkVersion(byte networkVersion) {
        this.networkVersion = networkVersion;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getGameDirectory() {
        return gameDirectory;
    }

    public void setGameDirectory(String gameDirectory) {
        this.gameDirectory = gameDirectory;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public void setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    public short getAppId() {
        return appId;
    }

    public void setAppId(short appId) {
        this.appId = appId;
    }

    public byte getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(byte numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public byte getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(byte maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public byte getNumOfBots() {
        return numOfBots;
    }

    public void setNumOfBots(byte numOfBots) {
        this.numOfBots = numOfBots;
    }

    public boolean isDedicated() {
        return dedicated;
    }

    public void setDedicated(boolean dedicated) {
        this.dedicated = dedicated;
    }

    public char getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(char operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public boolean isPasswordProtected() {
        return passwordProtected;
    }

    public void setPasswordProtected(boolean passwordProtected) {
        this.passwordProtected = passwordProtected;
    }

    public boolean isSecure() {
        return secure;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    public String getGameVersion() {
        return gameVersion;
    }

    public void setGameVersion(String gameVersion) {
        this.gameVersion = gameVersion;
    }

    public long getServerId() {
        return serverId;
    }

    public void setServerId(long serverId) {
        this.serverId = serverId;
    }

    public short getTvPort() {
        return tvPort;
    }

    public void setTvPort(short tvPort) {
        this.tvPort = tvPort;
    }

    public String getTvName() {
        return tvName;
    }

    public void setTvName(String tvName) {
        this.tvName = tvName;
    }

    public String getServerTags() {
        return serverTags;
    }

    public void setServerTags(String serverTags) {
        this.serverTags = serverTags;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public List<SourcePlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<SourcePlayer> players) {
        this.players = players;
    }

    public Map<String, String> getRules() {
        return rules;
    }

    public void setRules(Map<String, String> rules) {
        this.rules = rules;
    }
}
