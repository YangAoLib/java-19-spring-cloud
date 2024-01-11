package edu.yangao.config;

import org.springframework.cloud.gateway.filter.factory.RequestRateLimiterGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

@Configuration
public class RequestRateLimiterConfig {

    /**
     * 使用 ip 作为 限流的key
     * <p>
     * 限流网关过滤器工厂 需要一个默认的KeyResolver 如果容器中有多个bean 则需要使用 @Primary指定默认的bean
     * {@link RequestRateLimiterGatewayFilterFactory#RequestRateLimiterGatewayFilterFactory(RateLimiter, KeyResolver)}
     * </p>
     */
    @Bean
    @Primary
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

    /**
     * 使用 请求路径 作为 限流的key
     */
    @Bean
    public KeyResolver apiKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }
}
