package com.mcbanners.gameserverapi.game.service;

import com.ibasco.agql.protocols.valve.source.query.client.SourceQueryClient;
import com.ibasco.agql.protocols.valve.source.query.enums.SourceChallengeType;
import com.ibasco.agql.protocols.valve.source.query.pojos.SourcePlayer;
import com.ibasco.agql.protocols.valve.source.query.pojos.SourceServer;
import com.mcbanners.gameserverapi.game.status.GameStatus;
import com.mcbanners.gameserverapi.game.status.source.SourceGameStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@CacheConfig(cacheNames = {"source-game-service"})
public class SourceGameService extends GameService {
    public SourceGameService() {
        super(28015);
    }

    @Override
    @Cacheable
    public GameStatus getStatus(String hostname, int port) {
        CompletableFuture<SourceGameStatus> futureStatus = new CompletableFuture<>();
        SourceQueryClient sourceQueryClient = new SourceQueryClient();
        InetSocketAddress address = new InetSocketAddress(hostname, port);

        CompletableFuture<Integer> challengeFuture = sourceQueryClient.getServerChallenge(SourceChallengeType.PLAYER, address);

        challengeFuture.whenComplete((challenge, challengeError) -> {
            SourceGameStatus sourceGameStatus = new SourceGameStatus();
            if (challengeError != null) {
                System.out.println(challengeError.getMessage());
                return;
            }

            CompletableFuture<List<SourcePlayer>> playersFuture = sourceQueryClient.getPlayers(challenge, address);
            playersFuture.whenComplete((players, playerError) -> {
                if (playerError != null) {
                    System.out.println(playerError.getMessage());
                    return;
                }
                sourceGameStatus.setPlayers(players);
            });

            CompletableFuture<Map<String, String>> rulesFuture = sourceQueryClient.getServerRules(challenge, address);
            rulesFuture.whenComplete((rules, rulesError) -> {
                if (rulesError != null) {
                    System.out.println(rulesError.getMessage());
                    return;
                }
                sourceGameStatus.setRules(rules);
            });

            CompletableFuture<SourceServer> infoFuture = sourceQueryClient.getServerInfo(address);
            infoFuture.whenComplete((info, infoError) -> {
                if (infoError != null) {
                    System.out.println(infoError.getMessage());
                    return;
                }
                BeanUtils.copyProperties(info, sourceGameStatus);
            });
            futureStatus.complete(sourceGameStatus);
        }).join();

        try {
            return futureStatus.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }
}
