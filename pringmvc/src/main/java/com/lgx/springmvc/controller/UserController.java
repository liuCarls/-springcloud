package com.lgx.springmvc.controller;

import com.lgx.springmvc.entity.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = Logger.getLogger(UserController.class);

    /*
     *  http://localhost:8080/getUserInfo
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public User getUserInfo() {
        User user = new User(5,"Tom", 25, "ZH cn");

        if(user!=null){
            System.out.println("user.getName():"+user.getName());
            logger.info("user.getAge():"+user.getAge());
        }
        return user;
    }
    @RequestMapping("/list")
    public String  listUser(Model model) {
        logger.info("-----------------listUser------------------");
        List<User> userList = new ArrayList<User>();
        for (int i = 0; i <10; i++) {
            userList.add(new User(i,"张三"+i,20+i,"中国广州"));
        }
        model.addAttribute("users", userList);
        return "/user/list";
    }
}
