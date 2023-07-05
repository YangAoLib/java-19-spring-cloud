package edu.yangao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Eureka01EurekaServerDockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Eureka01EurekaServerDockerApplication.class, args);
    }

}
