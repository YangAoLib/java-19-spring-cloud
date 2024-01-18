package edu.yangao.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试配置中心 controller
 */
@RestController
public class TestConfigCenterController {

    @Value("${yangao.config.name}")
    private String name;
    @Value("${yangao.config.age}")
    private Integer age;
    @Value("${yangao.config.sex}")
    private String sex;

    @GetMapping("info")
    public String info() {
        return String.format("%s:%d:%s", name, age, sex);
    }
}
