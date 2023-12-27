package edu.yangao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class Hystrix03HedgehogApplication {

	public static void main(String[] args) {
		SpringApplication.run(Hystrix03HedgehogApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate ribbonRestTemplate() {
		return new RestTemplate();
	}
}
