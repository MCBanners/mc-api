package com.mcbanners.gameserverapi.game.service;

import com.ibasco.agql.protocols.valve.source.query.client.SourceQueryClient;
import com.ibasco.agql.protocols.valve.source.query.pojos.SourceServer;
import com.mcbanners.gameserverapi.game.status.GameStatus;
import com.mcbanners.gameserverapi.game.status.source.SourceGameStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;

@Service
@CacheConfig(cacheNames = {"source-game-service"})
public class SourceGameService extends GameService {
    public SourceGameService() {
        super(28015);
    }

    @Override
    public GameStatus getStatus(String hostname, int port) {
        SourceQueryClient sourceQueryClient = new SourceQueryClient();
        InetSocketAddress address = new InetSocketAddress(hostname, port);
        CompletableFuture<SourceServer> futureStatus = sourceQueryClient.getServerInfo(address);
        SourceGameStatus sourceGameStatus = new SourceGameStatus();

        futureStatus.whenComplete((info, error) -> {
            if (error != null) {
                System.out.println(error.getMessage());
                return;
            }

            info.setAddress(address);

            BeanUtils.copyProperties(info, sourceGameStatus);

            sourceGameStatus.setHost(address.getHostString());
            sourceGameStatus.setPort(address.getPort());


        }).join();
        return sourceGameStatus;
    }
}
