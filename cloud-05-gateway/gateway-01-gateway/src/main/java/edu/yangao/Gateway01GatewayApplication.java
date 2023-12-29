package edu.yangao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@SpringBootApplication
public class Gateway01GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(Gateway01GatewayApplication.class, args);
    }


    /**
     * bean 路由配置的优先级要高于配置文件
     * 如果要配置动态路由 则不要使用bean注入路由配置 否则动态路由会失效
     */
    // @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(r -> r.path("/login").uri("http://localhost:8080"))
                .build();
    }
}
