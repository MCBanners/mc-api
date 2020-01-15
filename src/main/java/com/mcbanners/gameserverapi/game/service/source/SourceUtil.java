package com.mcbanners.gameserverapi.utils;

import com.ibasco.agql.protocols.valve.source.query.client.SourceQueryClient;
import com.ibasco.agql.protocols.valve.source.query.pojos.SourceServer;
import com.mcbanners.gameserverapi.game.status.GameStatus;
import com.mcbanners.gameserverapi.game.status.source.SourceGameStatus;
import org.springframework.beans.BeanUtils;

import java.net.InetSocketAddress;
import java.util.Map;

public class SourceUtil {

    public static GameStatus query(String hostname, int port) {
        try (SourceQueryClient sourceQueryClient = new SourceQueryClient()) {
            SourceGameStatus status = new SourceGameStatus();
            InetSocketAddress address = new InetSocketAddress(hostname, port);
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
