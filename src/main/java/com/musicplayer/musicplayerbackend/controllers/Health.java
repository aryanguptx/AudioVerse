package com.musicplayer.musicplayerbackend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Health {

    @GetMapping("/api/v1/health")
    public String healthCheck(){
        return "200 OK";
    }
}
