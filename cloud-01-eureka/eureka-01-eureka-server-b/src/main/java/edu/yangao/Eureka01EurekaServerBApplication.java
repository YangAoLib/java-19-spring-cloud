package edu.yangao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Eureka01EurekaServerBApplication {

    public static void main(String[] args) {
        SpringApplication.run(Eureka01EurekaServerBApplication.class, args);
    }

}
