package com.mcbanners.gameserverapi.game.service.source;

import com.mcbanners.gameserverapi.game.SourceGameType;
import com.mcbanners.gameserverapi.game.service.GameService;
import com.mcbanners.gameserverapi.game.status.GameStatus;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = {"sd2d-game-service"})
public class SD2DGameService extends GameService {
    public SD2DGameService() {
        super(SourceGameType.SD2D.getPort());
    }

    @Override
    public GameStatus getStatus(String hostname, int port) {
        return SourceUtil.query(hostname, port);
    }
}
