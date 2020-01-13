package com.mcbanners.gameserverapi.controller;

import com.mcbanners.gameserverapi.game.GameType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gameserver/{type}/{host}/{port}")
public class GameServerController {
    @GetMapping("isValid")
    public void isValid(@PathVariable GameType type, @PathVariable String host, @PathVariable int port) {

    }

    @GetMapping("getData")
    public void getData(@PathVariable GameType type, @PathVariable String host, @PathVariable int port) {

    }
}
