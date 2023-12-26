package edu.yangao.feign;

import edu.yangao.feign.hystrix.RentCarFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 需要指定对应的错误回退类（备选方案）
 */
@FeignClient(value = "hystrix-rent-car-provider", fallback = RentCarFeignHystrix.class)
public interface RentCarFeign {

    @GetMapping("rent-car")
    String rentCar();
}
