package edu.yangao.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class LoginController {

    @Value("${server.port}")
    private Integer port;

    @GetMapping("login")
    public String login(@RequestParam(required = false) String name) {
        return port + ": " + name + ": " + UUID.randomUUID();
    }
}
