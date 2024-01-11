package edu.yangao.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * token校验
 */
@Component
public class TokenFilter implements GlobalFilter, Ordered {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public static final List<String> WHITE_LIST = Arrays.asList("/login-server/login");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 判断是否需要直接放行
        String path = request.getPath().value();
        // 在白名单中直接放行
        if (WHITE_LIST.contains(path)) {
            return chain.filter(exchange);
        }
        // 判断是否有token
        List<String> authorization = request.getHeaders().get("Authorization");
        // 如果有并且在redis中有对应的数据 则放行
        if (!CollectionUtils.isEmpty(authorization)) {
            String token = authorization.get(0);
            if (token != null && !token.isEmpty()) {
                if (token.startsWith("Bearer ")) {
                    token = token.replace("Bearer ", "");
                }
                // 判断其是否在redis中
                if (Boolean.TRUE.equals(redisTemplate.hasKey(token))) {
                    return chain.filter(exchange);
                }
            }
        }
        // 其他情况进行拦截
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
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
