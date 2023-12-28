package edu.yangao.feign.hystrix;

import edu.yangao.entity.Order;
import edu.yangao.feign.OrderFeign;
import org.springframework.stereotype.Component;

/**
 * order feign 熔断器
 */
@Component
public class OrderFeignHystrix implements OrderFeign {
    @Override
    public Order findOrder(Integer userId) {
        return null;
    }
}
