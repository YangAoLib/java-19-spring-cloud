package edu.yangao.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentCarController {

    @Value("${server.port}")
    private Integer port;

    @GetMapping("rent-car")
    public String rentCar() {
        return "rent car is ok\n租车成功" + port;
    }
}
