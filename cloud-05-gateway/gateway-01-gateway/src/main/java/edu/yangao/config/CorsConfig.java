package edu.yangao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * 跨域配置（代码配置方式）
 */
// @Configuration
public class CorsConfig {

    /**
     * 跨域
     */
    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许跨域携带Cookie
        corsConfiguration.setAllowCredentials(true);
        // 允许所有请求头
        corsConfiguration.addAllowedHeader("*");
        // 允许所有请求方式
        corsConfiguration.addAllowedMethod("*");
        // 允许所有源
        corsConfiguration.addAllowedOrigin("*");
        // 设置哪些路径允许跨域
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(urlBasedCorsConfigurationSource);
    }
}
