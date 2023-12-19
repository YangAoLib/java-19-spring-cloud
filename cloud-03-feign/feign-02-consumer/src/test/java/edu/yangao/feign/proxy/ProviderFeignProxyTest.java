package edu.yangao.feign.proxy;

import edu.yangao.feign.ProviderFeign;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Proxy;
import java.net.URI;

@SpringBootTest
public class ProviderFeignProxyTest {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     * 生产者 feign
     */
    private ProviderFeign providerFeign;

    @BeforeEach
    public void before() {
        // 通过代理创建生产者feign对象
        providerFeign = (ProviderFeign) Proxy.newProxyInstance(ProviderFeignProxyTest.class.getClassLoader(), new Class[]{ProviderFeign.class}, (proxy, method, args) -> {
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
