package edu.yangao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 生产者 controller
 */
@RestController
public class ProviderController {


    @GetMapping("info")
    public String info() {
        return "远程调用信息";
    }
}
