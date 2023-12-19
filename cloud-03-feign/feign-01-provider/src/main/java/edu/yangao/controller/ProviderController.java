package edu.yangao.controller;

import edu.yangao.entity.OrderInfo;
import edu.yangao.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Slf4j
public class ProviderController {

    /**
     * 运行端口
     */
    @Value("${server.port}")
    private Integer port;

    /**
     * get请求 无参
     */
    @GetMapping("nothing-provider")
    public Result<String> getNothingProvider() {
        return Result.success("操作成功" + port, "我是提供者");
    }

    /**
     * get请求 url（param）传参
     * @param name 名称
     */
    @GetMapping("param-provider")
    public Result<String> getParamProvider(@RequestParam("name") String name) {
        return Result.success("操作成功" + port, "我是提供者: " + name);
    }

    /**
     * get请求 path variable 传参
     * @param name 名称
     */
    @GetMapping("path-variable-provider/{name}")
    public Result<String> getPathVariableProvider(@PathVariable("name") String name) {
        return Result.success("操作成功" + port, "我是提供者: " + name);
    }

    /**
     * post 请求 body、param、path variable 传参
     * @param orderInfo 订单信息（body）
     * @param orderName 订单名（param）
     * @param id 订单ID（path variable）
     */
    @PostMapping("json-and-param-and-path-variable-provider/{id}")
    public Result<OrderInfo> postJsonAndParamAndPathVariableProvider(@RequestBody OrderInfo orderInfo, @RequestParam("order-name") String orderName, @PathVariable("id") Long id) {
        orderInfo.setOrderName(orderName);
        orderInfo.setId(id);
        return Result.success("操作成功" + port, orderInfo);
    }

    /**
     * get 请求 传日期参数
     * @param date 日期
     * @return 收到的日期
     */
    @GetMapping("get-date")
    public Result<Date> getDate(@RequestParam("date") Date date) {
        log.info("date: {}", date);
        return Result.success("操作成功" + port, date);
    }

}
