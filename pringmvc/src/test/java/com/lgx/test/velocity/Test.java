package com.lgx.test.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class Test {
    public static void main(String[] args) {
        try {
            VelocityEngine ve = new VelocityEngine();
            ve.setProperty(org.apache.velocity.runtime.RuntimeConstants.RESOURCE_LOADER, "classpath");
            ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
            // 初始化
            ve.init();

            Template template = ve.getTemplate("vm/domain.vm");
            VelocityContext ctx = new VelocityContext();
            ctx.put("package", "com.example.lx.springbootdemo.Velocity");
            ctx.put("className", "Test");
            ctx.put("Object", "Value");
            StringWriter sw = new StringWriter();
            template.merge(ctx, sw);
            String r = sw.toString();
            System.out.println(r);
            File file = new File("TestDomain.java");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.append(r);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
