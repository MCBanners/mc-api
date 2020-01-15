package com.mcbanners.gameserverapi.game;

import com.mcbanners.gameserverapi.game.service.MinecraftGameService;
import com.mcbanners.gameserverapi.game.service.source.ARKGameService;
import com.mcbanners.gameserverapi.game.service.source.CSGOGameService;
import com.mcbanners.gameserverapi.game.service.source.RustGameService;
import com.mcbanners.gameserverapi.game.service.source.SD2DGameService;
import com.mcbanners.gameserverapi.utils.spring.SpringContext;

public enum GameType {
    MINECRAFT(MinecraftGameService.class),
    RUST(RustGameService.class),
    CSGO(CSGOGameService.class),
    SD2D(SD2DGameService.class),
    ARK(ARKGameService.class);

    private Class<?> service;

    GameType(Class<?> service) {
        this.service = service;
    }

    @SuppressWarnings("unchecked")
    public <T> T getService() {
        return (T) SpringContext.getBean(service);
    }
}
