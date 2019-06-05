package com.lgx.springmvc;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    private Logger logger = Logger.getLogger(LoginController.class);
//    @RequestMapping("/login")
//    public String login(@RequestParam(value="username") String username,
//                        @RequestParam(value="password") String password,
//                        ModelMap model) {
//        logger.info("--------------login-----------"+username+"\t"+password);
//        model.addAttribute("host", "http://blog.didispace.com");
//        return "index";
//    }
    @RequestMapping("/")
    public String home1(){
        return "/home";
    }
    @RequestMapping("/home")
    public String home() {
        return "/home";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @RequestMapping("/user")
    public String user() {
        return "/user";
    }

    @RequestMapping("/about")
    public String about() {
        return "/about";
    }

    @RequestMapping("/login")
    public String login() {
        logger.info("Controller.login");
        return "/login";
    }

    @RequestMapping("/403")
    public String error403() {
        return "/error/403";
    }


}
