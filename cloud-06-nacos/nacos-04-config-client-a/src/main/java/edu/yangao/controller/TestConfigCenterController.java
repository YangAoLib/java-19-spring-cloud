package edu.yangao.controller;

import edu.yangao.config.YangAoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试配置中心 controller
 */
@RestController
public class TestConfigCenterController {

    @Autowired
    private YangAoConfig yangAo;

    @GetMapping("info")
    public String info() {
        return String.format("%s:%d:%s", yangAo.getName(), yangAo.getAge(), yangAo.getSex() );
    }
}
