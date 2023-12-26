package edu.yangao;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class Feign02ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Feign02ConsumerApplication.class, args);
    }

    /**
     * 设置feign日志级别（还需要在配置文件中开启日志打印）
     */
    @Bean
    public Logger.Level level() {
        return Logger.Level.FULL;
    }
}
