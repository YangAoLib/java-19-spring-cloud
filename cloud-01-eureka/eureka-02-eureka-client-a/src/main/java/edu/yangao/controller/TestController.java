package edu.yangao.controller;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YangAo
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(String serviceName) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);

        ServiceInstance serviceInstance = instances.get(0);
        String ip = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        System.out.println(ip + ":" + port);

        instances.forEach(System.out::println);
        return instances.get(0).toString();
    }

    @Resource
    private DiscoveryClient discoveryClient;
}
