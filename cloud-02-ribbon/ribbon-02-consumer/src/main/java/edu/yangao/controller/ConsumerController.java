package edu.yangao.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ConsumerController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("test-ribbon")
    public String testRibbon(String serviceName) {
        String url = "http://" + serviceName + "/hello";
        return restTemplate.getForObject(url, String.class);
    }

    @Resource
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("test-ribbon-rule")
    public String testRibbonRule(String serviceName) {
        ServiceInstance instance = loadBalancerClient.choose(serviceName);
        String url = instance.getUri() + "/hello";
        return new RestTemplate().getForObject(url, String.class);
    }
}
