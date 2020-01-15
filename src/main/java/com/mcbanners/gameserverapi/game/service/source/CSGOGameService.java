package com.mcbanners.gameserverapi.game.service.source;

import com.mcbanners.gameserverapi.game.SourceGameType;
import com.mcbanners.gameserverapi.game.service.GameService;
import com.mcbanners.gameserverapi.game.status.GameStatus;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = {"csgo-game-service"})
public class CSGOGameService extends GameService {

    public CSGOGameService() {
        super(SourceGameType.CSGO.getPort());
    }

    @Override
    @Cacheable
    public GameStatus getStatus(String hostname, int port) {
        return SourceUtil.query(hostname, port);
    }
}
