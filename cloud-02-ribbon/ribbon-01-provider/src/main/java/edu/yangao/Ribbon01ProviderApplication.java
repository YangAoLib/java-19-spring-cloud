package edu.yangao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Ribbon01ProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ribbon01ProviderApplication.class, args);
	}

}
