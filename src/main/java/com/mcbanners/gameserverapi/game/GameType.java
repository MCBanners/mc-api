package com.mcbanners.gameserverapi.game;

import com.mcbanners.gameserverapi.game.service.MinecraftGameService;
import com.mcbanners.gameserverapi.utils.spring.SpringContext;

public enum GameType {
    MINECRAFT(MinecraftGameService.class);

    private Class<?> service;

    GameType(Class<?> service) {
        this.service = service;
    }

    @SuppressWarnings("unchecked")
    public <T> T getService() {
        return (T) SpringContext.getBean(service);
    }
}
