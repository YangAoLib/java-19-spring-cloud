package edu.yangao.feign;

import edu.yangao.entity.OrderInfo;
import edu.yangao.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 使用 feign 需要在启动类上添加 @EnableFeignClients 开启 feign 功能
 * 使用时, 先添加一个接口, 然后在接口上面使用 @FeignClient注解
 */
@FeignClient("feign-provider")
public interface ProviderFeign {

    /**
     * get请求 无参
     */
    @GetMapping("nothing-provider")
    Result<String> getNothingProvider();

    /**
     * get请求 url（param）传参
     * @param name 名称
     */
    @GetMapping("param-provider")
    Result<String> getParamProvider(@RequestParam("name") String name);

    /**
     * get请求 path variable 传参
     * @param name 名称
     */
    @GetMapping("path-variable-provider/{name}")
    Result<String> getPathVariableProvider(@PathVariable("name") String name);

    /**
     * post 请求 body、param、path variable 传参
     * @param orderInfo 订单信息（body）
     * @param orderName 订单名（param）
     * @param id 订单ID（path variable）
     */
    @PostMapping("json-and-param-and-path-variable-provider/{id}")
    Result<OrderInfo> postJsonAndParamAndPathVariableProvider(@RequestBody OrderInfo orderInfo, @RequestParam("order-name") String orderName, @PathVariable("id") Long id);

    /**
     * get 请求 传日期参数
     * @param date 日期
     * @return 收到的日期
     */
    @GetMapping("get-date")
    Result<Date> getDate(@DateTimeFormat(pattern = "yyyy-MM-DD HH:mm:ss") @RequestParam("date") Date date);
}
