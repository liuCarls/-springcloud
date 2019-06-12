package com.lgx.springmvc.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * https://www.cnblogs.com/jtlgb/p/8532433.html
 * 1. 用@Configuration注解该类，等价于XML中配置beans；
 * 用@Bean标注方法等价于XML中配置bean。
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.lgx.springmvc";
    public static final String VERSION = "1.0.0";

    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.basePackage("cn.saytime.web"))
                .apis(RequestHandlerSelectors.basePackage("com.lgx.springmvc.swagger")) //api接口包扫描路径
                .paths(PathSelectors.any())//可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .build();
    }
    /**
        * 创建该API的基本信息（这些基本信息会展现在文档页面中）
        * 访问地址：http://项目实际地址/swagger-ui.html
        * @return
    */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger构建的api文档") //设置文档的标题
                .description("简单优雅的restfun风格") //设置文档的描述->1.Overview
                .version("1.0.0") //设置文档的版本信息-> 1.1 Version information
//                .contact(new Contact("Lgx", "http://localhost:8080/home","lgx@163.com")) //设置文档的联系方式->1.2 Contact information
                .contact("lgx") //设置文档的联系方式->1.2 Contact information
                .termsOfServiceUrl("http://www.baidu.com") //设置文档的License信息->1.3 License information
                .build();
    }
}
