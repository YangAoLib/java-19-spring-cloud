package edu.yangao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Eureka02EurekaClientAApplication {

    public static void main(String[] args) {
        SpringApplication.run(Eureka02EurekaClientAApplication.class, args);
    }

}
