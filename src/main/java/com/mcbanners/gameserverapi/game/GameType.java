package com.mcbanners.gameserverapi.game;

import com.mcbanners.gameserverapi.game.service.MinecraftGameService;
import com.mcbanners.gameserverapi.game.service.source.ARKGameService;
import com.mcbanners.gameserverapi.game.service.source.CSGOGameService;
import com.mcbanners.gameserverapi.game.service.source.GMODGameService;
import com.mcbanners.gameserverapi.game.service.source.RustGameService;
import com.mcbanners.gameserverapi.game.service.source.SD2DGameService;
import com.mcbanners.gameserverapi.game.service.source.TF2GameService;
import com.mcbanners.gameserverapi.utils.spring.SpringContext;

public enum GameType {
    MINECRAFT(MinecraftGameService.class, 25565),
    RUST(RustGameService.class, 28015),
    CSGO(CSGOGameService.class, 27015),
    SD2D(SD2DGameService.class, 26900),
    ARK(ARKGameService.class, 27015),
    TF2(TF2GameService.class, 27015),
    GMOD(GMODGameService.class, 27015);

    private Class<?> service;
    private final int port;

    GameType(Class<?> service, int port) {
        this.service = service;
        this.port = port;
    }

    @SuppressWarnings("unchecked")
    public <T> T getService() {
        return (T) SpringContext.getBean(service);
    }

    public int getPort() {
        return port;
    }
}
