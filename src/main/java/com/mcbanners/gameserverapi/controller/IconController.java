package com.mcbanners.gameserverapi.controller;

import com.mcbanners.gameserverapi.model.ServerStatus;
import com.mcbanners.gameserverapi.services.ServerStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequestMapping("icon")
public class IconController {
    private final ServerStatusService serverStatusService;

    @Autowired
    public IconController(ServerStatusService serverStatusService) {
        this.serverStatusService = serverStatusService;
    }

    @GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@RequestParam String host, @RequestParam int port) {
        ServerStatus status = serverStatusService.getStatus(host, port);
        if (status == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(Base64.getDecoder().decode(status.getIcon()), HttpStatus.OK);
        }
    }
}
