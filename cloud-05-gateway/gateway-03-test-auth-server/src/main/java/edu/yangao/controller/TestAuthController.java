package edu.yangao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAuthController {

    @GetMapping("test-auth")
    public String testAuth() {
        return "测试登录成功";
    }
}
