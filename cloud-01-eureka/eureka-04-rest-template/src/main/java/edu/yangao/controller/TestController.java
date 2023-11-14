package edu.yangao.controller;

import edu.yangao.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YangAo
 */
@RestController
public class TestController {


    @GetMapping("test-get")
    public String testGet(String name) {
        System.out.println(name);
        return "ok";
    }

    @PostMapping("test-post-json")
    public String testPost1(@RequestBody User user) {
        System.out.println(user);
        return "ok";
    }

    @PostMapping("test-post-form")
    public String testPostForm(User user) {
        System.out.println(user);
        return "ok";
    }
}
