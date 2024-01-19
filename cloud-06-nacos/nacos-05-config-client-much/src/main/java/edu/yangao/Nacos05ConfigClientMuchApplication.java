package edu.yangao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 同时读取多个配置文件
 */
@SpringBootApplication
public class Nacos05ConfigClientMuchApplication {

    public static void main(String[] args) {
        SpringApplication.run(Nacos05ConfigClientMuchApplication.class, args);
    }

}
