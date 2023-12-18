package edu.yangao.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("feign-provider")
public interface ProviderFeign {

    @GetMapping("provider")
    String provider();
}
