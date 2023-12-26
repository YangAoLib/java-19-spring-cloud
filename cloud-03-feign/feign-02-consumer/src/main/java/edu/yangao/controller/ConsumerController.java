package edu.yangao.controller;

import edu.yangao.entity.OrderInfo;
import edu.yangao.entity.Result;
import edu.yangao.feign.ProviderFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

    /**
     * get 请求 传日期参数 （feign日期传参会有误差, 所以推荐转成字符串或时间戳进行传输, 或者使用对象进行包裹然后通过body进行传参, 或者在请求和调用的地方都通过 {@link org.springframework.format.annotation.DateTimeFormat} 来指明日期格式化的方式）
     * 也可以使用 {@link java.time.LocalDate} 和 {@link java.time.LocalDateTime} 进行传参（但是LocalDateTime会丢失秒和毫秒）
     */
    @GetMapping("get-date")
    public Result<Date> getDate() {
        log.info("get 请求 传日期参数");
        Date date = new Date();
        log.info("date: {}", date);
        return providerFeign.getDate(date);
    }

}
