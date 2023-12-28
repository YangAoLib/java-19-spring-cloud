package edu.yangao.controller;

import edu.yangao.entity.Order;
import edu.yangao.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("find")
    public Order findOrder() {
        return orderFeign.findOrder(1);
    }

    @Autowired
    @Qualifier("edu.yangao.feign.OrderFeign")
    private OrderFeign orderFeign;
}
