package edu.yangao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Gateway01GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(Gateway01GatewayApplication.class, args);
    }

}
