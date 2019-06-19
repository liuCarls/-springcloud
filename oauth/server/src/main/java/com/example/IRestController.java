package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class IRestController {
    @RequestMapping({ "/user", "/me" })
    public Map<String, String> user(Principal principal) {
        Map<String, String> map = new LinkedHashMap<>();
        if (principal != null) {
            map.put("name", principal.getName()+":SERVER");
        }
        return map;
    }
    @RequestMapping("/hello")
    public String Hello() {
        return "hello word!";
    }
}
