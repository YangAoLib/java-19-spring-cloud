package edu.yangao;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class Gateway01GatewayApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(LocalDateTime.now());
    }

}
