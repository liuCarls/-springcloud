package com.lgx.springmvc;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 请求入口Controller部分提供三种接口样例：视图模板，Json，restful风格
 */
@Controller
public class HelloController {
    private Logger logger = Logger.getLogger(HelloController.class);
    @RequestMapping("/hello")
    public String greeting(@RequestParam(value="name", required=false,
            defaultValue="World") String name,
            ModelMap model) {
        logger.info("hello");
        // 加入一个属性，用来在模板中读取
        model.addAttribute("host", "http://blog.didispace.com");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }

//    @RequestMapping("/authentication/form")
//    public String auth(@RequestParam String username,
//                       @RequestParam String password) {
//        logger.info(username+"\t"+password);
//        return "index";
//    }
//    @RequestMapping("/loginPage")
//    public String loginP() {
//        logger.info("--------------loginPage--------------");
//        return "login";
//    }

//    @RequestMapping("/logout")
//    public void logout(HttpServletRequest request,
//                       HttpServletResponse response) {
//        logger.info("-----------------logout------------------");
//        SecurityContextHolder.clearContext();
//
//    }

}
