package com.lgx.springmvc.activity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liuruijie on 2017/5/12.
 */
//@Controller
public class PageController {
    @RequestMapping("editor")
    public String test(){
        return "/modeler";
    }
}
