package edu.yangao.controller;

import edu.yangao.feign.ProviderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    private ProviderFeign providerFeign;

    @GetMapping("consumer")
    public String consumer() {
        System.out.println("消费者操作");
        return providerFeign.provider();
    }
}
