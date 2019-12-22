package com.mcbanners.mcapi.services;

import com.github.steveice10.mc.protocol.MinecraftConstants;
import com.github.steveice10.mc.protocol.MinecraftProtocol;
import com.github.steveice10.mc.protocol.data.SubProtocol;
import com.github.steveice10.mc.protocol.data.status.handler.ServerInfoHandler;
import com.github.steveice10.packetlib.Client;
import com.github.steveice10.packetlib.tcp.TcpSessionFactory;
import com.google.common.net.HostAndPort;
import com.mcbanners.mcapi.model.ServerStatus;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@CacheConfig(cacheNames = {"servers"})
public class ServerStatusService {

    @Cacheable
    public ServerStatus getStatus(HostAndPort hostAndPort) {
        final int port = 25565;

        final CompletableFuture<ServerStatus> infoFuture = new CompletableFuture<>();

        final MinecraftProtocol mcProto = new MinecraftProtocol(SubProtocol.STATUS);
        Client client = new Client(hostAndPort.getHost(), port, mcProto, new TcpSessionFactory());
        client.getSession().setFlag(MinecraftConstants.SERVER_INFO_HANDLER_KEY, (ServerInfoHandler) (session, info) -> {
            final ServerStatus status = new ServerStatus();
            status.setHost(hostAndPort.getHost());
            status.setPort(port);
            status.setVersion(info.getVersionInfo().getVersionName());
            infoFuture.complete(status);
        });
        client.getSession().connect();
        try {
            return infoFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Cacheable
    public ServerStatus getStatus(String host, int port) {
        return getStatus(HostAndPort.fromParts(host, port));
    }
}
