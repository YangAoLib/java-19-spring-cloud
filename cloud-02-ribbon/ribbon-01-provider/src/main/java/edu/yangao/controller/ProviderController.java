package edu.yangao.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    @Value("${server.port}")
    private Integer port;

    @GetMapping("hello")
    public String testProvider() {
        return "这是" + port + "的服务";
    }
}
