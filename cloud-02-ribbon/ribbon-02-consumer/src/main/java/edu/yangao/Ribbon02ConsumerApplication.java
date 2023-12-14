package edu.yangao;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class Ribbon02ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ribbon02ConsumerApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/**
	 * 替换全局的ribbon负载均衡默认规则
	 * @return 随机规则
	 */
	/* @Bean
	public IRule ribbonRule() {
		return new RandomRule();
	} */
}
