package edu.yangao.controller;

import edu.yangao.entity.Order;
import edu.yangao.feign.OrderFeign;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController implements OrderFeign {

    @Override
    public Order findOrder(@RequestParam Integer userId) {
        return Order.builder()
                .orderId(1)
                .userId(userId)
                .orderName("订单名称")
                .orderPrice(100.0)
                .build();
    }
}
