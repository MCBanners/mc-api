package com.mcbanners.gameserverapi.game.service;

import com.ibasco.agql.protocols.valve.source.query.client.SourceQueryClient;
import com.ibasco.agql.protocols.valve.source.query.pojos.SourceServer;
import com.mcbanners.gameserverapi.game.status.GameStatus;
import com.mcbanners.gameserverapi.game.status.source.SourceGameStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@CacheConfig(cacheNames = {"source-game-service"})
public class SourceGameService extends GameService {
    public SourceGameService() {
        super(28015);
    }

    @Override
    @Cacheable
    public GameStatus getStatus(String hostname, int port) {
        InetSocketAddress address = new InetSocketAddress(hostname, port);

        try (SourceQueryClient sourceQueryClient = new SourceQueryClient()) {
            SourceGameStatus status = new SourceGameStatus();
            SourceServer server = sourceQueryClient.getServerInfo(address).get();
            status.setPlayers(sourceQueryClient.getPlayersCached(address).get());
            Map<String, String> rules = sourceQueryClient.getServerRulesCached(address).get();
            status.setRules(rules);
            // Set this cause for some reason it doesn't
            server.setAddress(address);
            // Copy stuff over
            BeanUtils.copyProperties(server, status);
            // Update this
            status.setHost(address.getHostString());
            status.setPort(address.getPort());
            status.setIcon(rules.get("headerimage"));
            return status;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
