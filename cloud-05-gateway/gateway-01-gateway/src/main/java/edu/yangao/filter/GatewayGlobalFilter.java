package edu.yangao.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * 只能通过实现{@link Ordered}接口的方式来进行执行顺序的定义
 */
@Component
public class GatewayGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求信息
        ServerHttpRequest request = exchange.getRequest();
        System.out.println(request.getPath());
        System.out.println(request.getHeaders());
        System.out.println(request.getRemoteAddress().getHostName());
        System.out.println(request.getQueryParams());

        ServerHttpResponse response = exchange.getResponse();
        // 拦截请求 返回错误信息
        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpStatus.UNAUTHORIZED.value());
        map.put("msg", "未授权");
        byte[] bytes = new byte[0];
        try {
            bytes = objectMapper.writeValueAsBytes(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        DataBuffer wrap = response.bufferFactory().wrap(bytes);
        // 设置编码
        response.getHeaders().add("content-type", "application/json;charset=utf8");
        return response.writeWith(Mono.just(wrap));
        // 放行
        // return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
