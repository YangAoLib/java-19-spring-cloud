package edu.yangao.feign;

import edu.yangao.entity.OrderInfo;
import edu.yangao.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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

}
