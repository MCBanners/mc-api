package com.mcbanners.gameserverapi.controller;

import com.mcbanners.gameserverapi.game.GameType;
import com.mcbanners.gameserverapi.game.service.GameService;
import com.mcbanners.gameserverapi.game.status.GameStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/gameserver/{type}/{host}/{port}")
public class GameServerController {
    @GetMapping(value = "/check", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Boolean> isValid(@PathVariable GameType type, @PathVariable String host, @PathVariable int port) {
        return Collections.singletonMap(
                "valid",
                ((GameService) type.getService()).getStatus(host, port) != null
        );
    }

    @GetMapping(value = "/icon", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getIcon(@PathVariable GameType type, @PathVariable String host, @PathVariable int port) {
        GameStatus status = ((GameService) type.getService()).getStatus(host, port);
        if (status == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(Base64.getDecoder().decode(status.getIcon()), HttpStatus.OK);
    }

    @GetMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<? extends GameStatus> getData(@PathVariable GameType type, @PathVariable String host, @PathVariable int port) {
        GameStatus status = ((GameService) type.getService()).getStatus(host, port);
        if (status == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
