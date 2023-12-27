package edu.yangao.controller;

import edu.yangao.hedgehog.anno.Hedgehog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HedgehogController {

    @Autowired
    private RestTemplate restTemplate;

    public static final String SERVICE_NAME = "hystrix-rent-car-provider";

    @GetMapping("rent-car")
    @Hedgehog(SERVICE_NAME)
    public String rentCar() {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/rent-car", String.class);
    }
}
