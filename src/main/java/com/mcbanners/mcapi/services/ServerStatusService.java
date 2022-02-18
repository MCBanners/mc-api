package com.mcbanners.mcapi.services;

import com.github.steveice10.mc.protocol.MinecraftConstants;
import com.github.steveice10.mc.protocol.MinecraftProtocol;
import com.github.steveice10.mc.protocol.data.status.handler.ServerInfoHandler;
import com.github.steveice10.packetlib.tcp.TcpClientSession;
import com.google.common.net.HostAndPort;
import com.mcbanners.mcapi.model.Motd;
import com.mcbanners.mcapi.model.ServerStatus;
import com.mcbanners.mcapi.utils.MotdUtils;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
        final MinecraftProtocol mcProto = new MinecraftProtocol();

        final TcpClientSession client = new TcpClientSession(hostAndPort.getHost(), port, mcProto);
        client.setFlag(MinecraftConstants.SERVER_INFO_HANDLER_KEY, (ServerInfoHandler) (session, info) -> {
            final ServerStatus status = new ServerStatus();

            status.setHost(hostAndPort.getHost());
            status.setPort(port);
            status.setVersion(MotdUtils.clean(info.getVersionInfo().getVersionName()));
            status.getPlayers().setOnline(info.getPlayerInfo().getOnlinePlayers());
            status.getPlayers().setMax(info.getPlayerInfo().getMaxPlayers());

            final String raw = LegacyComponentSerializer.legacySection().serialize(info.getDescription()).trim();

            status.setMotd(new Motd());
            status.getMotd().setRaw(raw);
            status.getMotd().setColorless(MotdUtils.stripColors(raw));
            status.getMotd().setFormatted(MotdUtils.clean(raw));

            if (info.getIconPng() != null) {
                status.setIcon(Base64.getEncoder().encodeToString(info.getIconPng()));
            }

            infoFuture.complete(status);
        });

        client.connect();

        try {
            return infoFuture.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            if (client.isConnected()) {
                client.disconnect("timeout");
            }
        }
        return null;
    }
}
