package edu.yangao.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import edu.yangao.feign.RentCarFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    @Qualifier("edu.yangao.feign.RentCarFeign")
    private RentCarFeign rentCarFeign;

    @GetMapping("consumer-rent-car")
    public String consumerToRentCar() {
        System.out.println("用户去租车");
        return rentCarFeign.rentCar();
    }


    @GetMapping("consumer-ribbon-rent-car")
    @HystrixCommand(fallbackMethod = "consumerRibbonToRentCarHystrix")
    public String consumerRibbonToRentCar() {
        String serviceId = "hystrix-rent-car-provider";
        return ribbonRestTemplate.getForObject("http://" + serviceId + "/rent-car", String.class);
    }
    public String consumerRibbonToRentCarHystrix() {
        return "ribbon 熔断器: 租车失败";
    }

    @Autowired
    private RestTemplate ribbonRestTemplate;
}
