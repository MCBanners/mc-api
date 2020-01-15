package com.mcbanners.gameserverapi.game.service.source;

import com.mcbanners.gameserverapi.game.SourceGameType;
import com.mcbanners.gameserverapi.game.service.GameService;
import com.mcbanners.gameserverapi.game.status.GameStatus;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = {"ark-game-service"})
public class ARKGameService extends GameService {
    public ARKGameService() {
        super(SourceGameType.ARK.getPort());
    }

    @Override
    @Cacheable
    public GameStatus getStatus(String hostname, int port) {
        return SourceUtil.query(hostname, port);
    }
}
