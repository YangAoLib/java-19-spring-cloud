package edu.yangao.controller;

import edu.yangao.entity.OrderInfo;
import edu.yangao.entity.Result;
import edu.yangao.feign.ProviderFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class ConsumerController {

    @Autowired
    private ProviderFeign providerFeign;

    /**
     * get请求 无参
     */
    @GetMapping("nothing-provider")
    public Result<String> getNothingProvider() {
        log.info("get请求 无参");
        return providerFeign.getNothingProvider();
    }

    /**
     * get请求 url（param）传参
     */
    @GetMapping("param-provider")
    public Result<String> getParamProvider() {
        log.info("get请求 url（param）传参");
        return providerFeign.getParamProvider("消费者测试名称");
    }

    /**
     * get请求 path variable 传参
     */
    @GetMapping("path-variable-provider")
    public Result<String> getPathVariableProvider() {
        log.info("get请求 path variable 传参");
        return providerFeign.getPathVariableProvider("消费者测试名称");
    }

    /**
     * post 请求 body、param、path variable 传参
     */
    @PostMapping("json-and-param-and-path-variable-provider")
    public Result<OrderInfo> postJsonAndParamAndPathVariableProvider() {
        log.info("post 请求 body、param、path variable 传参");
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderNumber("测试编号");
        return providerFeign.postJsonAndParamAndPathVariableProvider(orderInfo, "消费者测试名称", 15L);
    }

}