package edu.yangao.feign.cglib;

import edu.yangao.feign.ProviderFeign;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@SpringBootTest
public class ProviderFeignCglibTest {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    private ProviderFeign providerFeign;

    @BeforeEach
    public void before() {
        providerFeign = (ProviderFeign) Enhancer.create(Object.class, new Class[]{ProviderFeign.class}, (MethodInterceptor) (o, method, objects, methodProxy) -> {
            // 获取方法声明的请求路径
            GetMapping getMapping = method.getAnnotation(GetMapping.class);
            String path = "/" + getMapping.value()[0];
            // 获取方法所在接口的 FeignClient 所声明的对应生产者名称
            Class<?> declaringClass = method.getDeclaringClass();
            FeignClient feignClient = declaringClass.getAnnotation(FeignClient.class);
            String serviceName = feignClient.value();
            // 通过负载均衡获取请求地址
            ServiceInstance choose = loadBalancerClient.choose(serviceName);
            URI uri = choose.getUri();
            // 发起请求
            RestTemplate restTemplate = new RestTemplate();
            URI resolve = uri.resolve(path).normalize();
            return restTemplate.getForObject(resolve, method.getReturnType());
        });
    }

    @Test
    void testInvoke() {
        System.out.println(providerFeign.getNothingProvider());
    }
}
