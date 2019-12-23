package com.mcbanners.mcapi.controller;

import com.mcbanners.mcapi.model.ServerStatus;
import com.mcbanners.mcapi.services.ServerStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {
    private final ServerStatusService serverStatusService;

    @Autowired
    public ServerController(ServerStatusService serverStatusService) {
        this.serverStatusService = serverStatusService;
    }

    @GetMapping("/server")
    public ResponseEntity<ServerStatus> getStatus(@RequestParam String host, @RequestParam(defaultValue = "25565") int port) {
        ServerStatus status = serverStatusService.getStatus(host, port);
        if (status == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            return new ResponseEntity<>(status, HttpStatus.OK);
        }
    }

}
