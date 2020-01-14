package com.mcbanners.gameserverapi.game.service;

import com.github.steveice10.mc.protocol.MinecraftConstants;
import com.github.steveice10.mc.protocol.MinecraftProtocol;
import com.github.steveice10.mc.protocol.data.SubProtocol;
import com.github.steveice10.mc.protocol.data.status.handler.ServerInfoHandler;
import com.github.steveice10.packetlib.Client;
import com.github.steveice10.packetlib.tcp.TcpSessionFactory;
import com.mcbanners.gameserverapi.game.status.GameStatus;
import com.mcbanners.gameserverapi.game.status.minecraft.MessageOfTheDay;
import com.mcbanners.gameserverapi.game.status.minecraft.MinecraftGameStatus;
import com.mcbanners.gameserverapi.game.status.minecraft.PlayerInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@CacheConfig(cacheNames = {"minecraft-game-service"})
public class MinecraftGameService extends GameService {
    public MinecraftGameService() {
        super(25565);
    }

    @Override
    public GameStatus getStatus(String hostname, int port) {
        CompletableFuture<MinecraftGameStatus> futureStatus = new CompletableFuture<>();
        MinecraftProtocol proto = new MinecraftProtocol(SubProtocol.STATUS);

        Client client = new Client(hostname, port, proto, new TcpSessionFactory());
        client.getSession().setFlag(MinecraftConstants.SERVER_INFO_HANDLER_KEY, (ServerInfoHandler) (session, info) -> {
            MinecraftGameStatus status = new MinecraftGameStatus();
            status.setHost(hostname);
            status.setPort(port);
            status.setVersion(info.getVersionInfo().getVersionName());

            status.setPlayers(new PlayerInfo()
                    .setOnline(info.getPlayerInfo().getOnlinePlayers())
                    .setMax(info.getPlayerInfo().getMaxPlayers())
            );

            status.setMotd(new MessageOfTheDay().setRaw(info.getDescription().getText().trim()).autoformat());

            if (info.getIcon() != null) {
                try (ByteArrayOutputStream icon = new ByteArrayOutputStream()) {
                    ImageIO.write(info.getIcon(), "png", icon);
                    status.setIcon(Base64.getEncoder().encodeToString(icon.toByteArray()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            futureStatus.complete(status);
        });

        client.getSession().connect();

        try {
            return futureStatus.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        } catch (TimeoutException ex) {
            if (client.getSession().isConnected()) {
                client.getSession().disconnect("Connection timed out");
            }
        }

        return null;
    }
}
