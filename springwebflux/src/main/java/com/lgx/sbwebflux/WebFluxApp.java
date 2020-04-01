package com.lgx.sbwebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1. 创建一个HandlerFunction返回值为Mono<ServerResponse>, 其中ServerResponse
 *   是相应的封装对象，包含了响应状态，响应头等等
 * 2. 还需要配置一个路由来类似@RequestMapping的功能，通过
 *   RouterFunctions.route(RequestPredicate, HandlerFunction)提供
 *   了一个路由器函数默认实现
 */
@SpringBootApplication
public class WebFluxApp {
    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(WebFluxApp.class,args);

    }
}
