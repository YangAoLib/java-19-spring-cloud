package edu.yangao.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * ip 校验 过滤器 （先于全局过滤器执行）
 */
@Component
public class IPCheckFilter implements GlobalFilter, Ordered {

    @Autowired
    private ObjectMapper objectMapper;

    private static final List<String> BLACK_LIST = Arrays.asList("localhost", "0:0:0:0:0:0:0:1");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取ip
        String ip = Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getHostName();
        // 如果不在黑名单中则放行
        if (!BLACK_LIST.contains(ip)) {
            return chain.filter(exchange);
        }
        // 在黑名单中则进行拦截
        // 生成返回信息
        HashMap<String, Object> map = new HashMap<>(4);
        map.put("code", 438);
        map.put("msg", "您在黑名单中");
        byte[] body;
        try {
            body = objectMapper.writeValueAsBytes(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        // 设置响应编码
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().add("content-type", "application/json;charset=utf-8");
        // 返回拦截信息
        DataBuffer dataBuffer = response.bufferFactory().wrap(body);
        return response.writeWith(Mono.just(dataBuffer));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
