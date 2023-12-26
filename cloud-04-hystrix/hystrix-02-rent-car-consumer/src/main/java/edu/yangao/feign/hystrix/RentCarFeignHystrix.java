package edu.yangao.feign.hystrix;

import edu.yangao.feign.RentCarFeign;
import org.springframework.stereotype.Component;

@Component
public class RentCarFeignHystrix implements RentCarFeign {

    @Override
    public String rentCar() {
        return "租车失败";
    }
}
