package edu.yangao.controller;

import edu.yangao.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class LoginController {

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("login")
    public String login(@RequestParam(required = false) String name) {
        // 生成用户信息 存储到redis
        UserInfo userInfo = new UserInfo(1, name, "qwfp", 18);
        UUID uuid = UUID.randomUUID();
        redisTemplate.opsForValue().set(uuid.toString(), userInfo.toString());
        return port + ": " + name + ": " + uuid;
    }
}
