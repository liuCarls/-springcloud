package com.lgx.springmvc;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * REST 指的是一组架构约束条件和原则。满足这些约束条件和原则的应用程序或设计就是 RESTful。
 *
 * 此外，有一款RESTFUL接口的文档在线自动生成+功能测试功能软件——Swagger UI，
 * 具体配置过程可移步《Spring Boot 利用 Swagger 实现restful测试》
 *
 * https://blog.csdn.net/weixin_37509652/article/details/80094370
 */
@RestController
@RequestMapping(value="/users")
public class SwaggerController {

    /*
     *  http://localhost:8080/swagger/index.html
     */

    @ApiOperation(value="Get all users",notes="requires noting")
    @RequestMapping(value = "user/{id}",method= RequestMethod.GET)
    public List<User> getUsers(){
        List<User> list=new ArrayList<User>();

        User user=new User();
        user.setName("hello");
        list.add(user);

        User user2=new User();
        user.setName("world");
        list.add(user2);
        return list;
    }

    @ApiOperation(value="Get user with id",notes="requires the id of user")
    @ApiImplicitParam(name = "name", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value="/{name}",method=RequestMethod.GET)
    public User getUserById(@PathVariable String name){
        User user=new User();
        user.setName(name);
        user.setAddress("hello world");
        return user;
    }
}
