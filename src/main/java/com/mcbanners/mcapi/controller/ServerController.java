package com.mcbanners.mcapi.controller;

import com.mcbanners.mcapi.model.ServerStatus;
import com.mcbanners.mcapi.services.ServerStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("server")
public class ServerController {
    private final ServerStatusService serverStatusService;

    @Autowired
    public ServerController(ServerStatusService serverStatusService) {
        this.serverStatusService = serverStatusService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServerStatus> getStatus(@RequestParam String host, @RequestParam int port) {
        ServerStatus status = serverStatusService.getStatus(host, port);
        if (status == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(status, HttpStatus.OK);
        }
    }

}
