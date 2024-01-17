package edu.yangao.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("nacos-client-b")
public interface ProviderFeign {

    @GetMapping("info")
    String info();
}
