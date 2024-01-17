package edu.yangao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Nacos02ClientBApplication {

    public static void main(String[] args) {
        SpringApplication.run(Nacos02ClientBApplication.class, args);
    }

}
