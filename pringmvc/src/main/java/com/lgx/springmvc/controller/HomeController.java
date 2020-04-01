package com.lgx.springmvc.controller;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
    private Logger logger = Logger.getLogger(HomeController.class);
//    @RequestMapping("/login")
//    public String login(@RequestParam(value="username") String username,
//                        @RequestParam(value="password") String password,
//                        ModelMap model) {
//        logger.info("--------------login-----------"+username+"\t"+password);
//        model.addAttribute("host", "http://blog.didispace.com");
//        return "index";
//    }
    @RequestMapping(value = {"/home","/"})
    public String home(Model model) {
        model.addAttribute("tmplName","securitytest");
        model.addAttribute("demoSel","securityDiv");
        return "/homep";
    }

    @RequestMapping("/admin")
    public String admin(Model model) {
//        return "/adminp";
        model.addAttribute("tmplName","userp");
        model.addAttribute("demoSel","welUser");
        return "/homep";
    }

    @RequestMapping("/user")
    public String user(Model model) {
//        return "/userp";
        model.addAttribute("tmplName","userp");
        model.addAttribute("demoSel","welUser");
        return "/homep";
    }

    @RequestMapping("/about")
    public String about() {
        return "/about";
    }

    @RequestMapping("/login")
    public String login() {
        logger.info("=========Controller.login=============");
        return "/loginp";
    }
    @RequestMapping("/loginp")
    public String login2(Model model) {
        logger.info("=========Controller.loginp=============");
        model.addAttribute("tmplName","securitytest");
        model.addAttribute("demoSel","securityDiv");
        return "/homep";
    }
    @RequestMapping("/wf")
    public String workflow(Model model) {
        logger.info("Controller.login");
        model.addAttribute("tmplName","workflow");
        model.addAttribute("demoSel","wfMg");
        return "/homep";
//        return "/workflow";
    }
    @RequestMapping("/403")
    public String error403() {
        return "/error/403";
    }

    @RequestMapping("/logout")
    public String logout(Model model,HttpServletRequest request,
                       HttpServletResponse response) {
        logger.info("-----------------logout------------------");
        SecurityContextHolder.clearContext();
        model.addAttribute("tmplName","securitytest");
        model.addAttribute("demoSel","securityDiv");
        return "/homep";
    }
}
