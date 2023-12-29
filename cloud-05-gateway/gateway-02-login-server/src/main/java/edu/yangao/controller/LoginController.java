package edu.yangao.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class LoginController {

    @Value("${server.port}")
    private Integer port;

    @GetMapping("login")
    public String login() {
        return port + ": " + UUID.randomUUID();
    }
}
