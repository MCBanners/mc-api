package com.mcbanners.mcapi.services;

import com.github.steveice10.mc.protocol.MinecraftConstants;
import com.github.steveice10.mc.protocol.MinecraftProtocol;
import com.github.steveice10.mc.protocol.data.SubProtocol;
import com.github.steveice10.mc.protocol.data.status.handler.ServerInfoHandler;
import com.github.steveice10.packetlib.Client;
import com.github.steveice10.packetlib.tcp.TcpSessionFactory;
import com.google.common.net.HostAndPort;
import com.mcbanners.mcapi.model.Motd;
import com.mcbanners.mcapi.model.ServerStatus;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
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
@CacheConfig(cacheNames = {"servers"})
public class ServerStatusService {


    @Cacheable
    public ServerStatus getStatus(String host, int port) {
        return getStatus(HostAndPort.fromParts(host, port));
    }

    private ServerStatus getStatus(HostAndPort hostAndPort) {
        final int port = hostAndPort.getPortOrDefault(25565);

        final CompletableFuture<ServerStatus> infoFuture = new CompletableFuture<>();

        final MinecraftProtocol mcProto = new MinecraftProtocol(SubProtocol.STATUS);

        Client client = new Client(hostAndPort.getHost(), port, mcProto, new TcpSessionFactory());
        client.getSession().setFlag(MinecraftConstants.SERVER_INFO_HANDLER_KEY, (ServerInfoHandler) (session, info) -> {
            final ServerStatus status = new ServerStatus();

            status.setHost(hostAndPort.getHost());
            status.setPort(port);
            status.setVersion(info.getVersionInfo().getVersionName());
            status.getPlayers().setOnline(info.getPlayerInfo().getOnlinePlayers());
            status.getPlayers().setMax(info.getPlayerInfo().getMaxPlayers());

            status.setMotd(new Motd());

            status.getMotd().setRaw(info.getDescription().getText().trim());
            status.getMotd().setFormatted("Coming Soon");

            final ByteArrayOutputStream icon = new ByteArrayOutputStream();
            if (info.getIcon() != null) {
                try {
                    ImageIO.write(info.getIcon(), "png", icon);
                    status.setIcon(Base64.getEncoder().encodeToString(icon.toByteArray()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            infoFuture.complete(status);
        });

        client.getSession().connect();

        try {
            return infoFuture.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            if (client.getSession().isConnected()) {
                client.getSession().disconnect("timeout");
            }
        }
        return null;
    }
}
