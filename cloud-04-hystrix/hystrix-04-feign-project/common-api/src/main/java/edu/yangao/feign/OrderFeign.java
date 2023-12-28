package edu.yangao.feign;

import edu.yangao.entity.Order;
import edu.yangao.feign.hystrix.OrderFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 继承的方式 无法在类上使用 @RequestMapping 注解, 如果使用Spring会将其作为一个Controller来处理（因为其会注入到容器中进而被识别）
 */
@FeignClient(value = "hystrix-feign-project-service-order", fallback = OrderFeignHystrix.class)
public interface OrderFeign {

    /**
     * 根据用户ID查询订单信息
     *
     * @param userId 用户ID
     * @return 订单信息
     */
    @GetMapping("/order/find")
    Order findOrder(@RequestParam Integer userId);
}
