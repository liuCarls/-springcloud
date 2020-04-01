package com.lgx.springmvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 默认的静态资源目录配置在spring-boot-autoconfigurejar包下的
 * org.springframework.boot.autoconfigure.web包下ResourceProperties类
 *分别在根目录，即/src/main/resources/目录下的/META-INF/resources/、
 * /resources/、/static/、/public/目录下
 * https://blog.csdn.net/liuxiao723846/article/details/80494157
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将static目录下的文件映射到根路径下。
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        /**
         * 把/swagger-ui.html路径映射到对应的目录META-INF/resources/下面
         */
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
