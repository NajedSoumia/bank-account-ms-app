package org.soumia.customerservice.web;

import org.soumia.customerservice.config.GlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ConfigTestController {
    @Value("${global.params.a1}")
    private int p1;
    @Value("${global.params.b1}")
    private int p2;
    @Value("${customer.params.x}")
    private int x;
    @Value("${customer.params.y}")
    private int y;

    @Autowired
    private GlobalConfig globalConfig;

    @GetMapping("/testConfig")
    public Map<String,Integer> configTest(){
        return Map.of("a1",p1,"a2",p2,"x",x,"y",y);
    }

    @GetMapping("/globalConfig")
    public GlobalConfig getGlobalConfig() {
        return globalConfig;
    }
}
