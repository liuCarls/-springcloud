package com.lgx.springcloud.feign;

import com.netflix.hystrix.HystrixCircuitBreaker;
import com.netflix.hystrix.HystrixCommandKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @Autowired
    HelloWorldService helloWorldFeignService;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String sayHello(){
        String result = null;
        try {
            result = helloWorldFeignService.sayHello();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HystrixCircuitBreaker breaker = HystrixCircuitBreaker.Factory
                .getInstance(HystrixCommandKey.Factory.asKey("HelloWorldService#sayHello()"));
        if (breaker != null) {
            System.out.println("断路器状态：" + breaker.isOpen());
        }
        return result;
    }
}
