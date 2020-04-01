package com.lgx.test.thymeleaf;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        String template = "<p th:text='${title}'></p>";
        Map<String, Object> params = new HashMap<>();
        params.put("title", "Thymeleaf 渲染 HTML ---- Anoy");
        TemplateEngine templateEngine = new TemplateEngine();
        Context context = new Context();
        context.setVariables(params);
        System.out.println(templateEngine.process(template, context));
    }
}
