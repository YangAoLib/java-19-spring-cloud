package edu.yangao.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.yangao.feign.ProviderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试远程访问 controller
 */
@RestController
public class AccessRemoteTestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProviderFeign providerFeign;

    @Value("${server.port}")
    private Integer port;

    @GetMapping("get-remote-info")
    public String getRemoteInfo(@RequestParam String serviceId) throws JsonProcessingException {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        System.out.println(objectMapper.writeValueAsString(instances));
        return providerFeign.info() + port;
    }
}
