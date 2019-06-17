package com.lgx.springmvc.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源服务器
 * 决定了哪些资源需要什么样的权限
 */
@Configuration
@EnableResourceServer
public class MyResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private AuthenticationSuccessHandler_oauth2 myAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailHandler_oauth2 myAuthenticationFailHandler;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //表单登录 方式
        http.formLogin()
            .loginPage("/login")
            //登录需要经过的url请求
            .loginProcessingUrl("/authentication/form")
            .successHandler(myAuthenticationSuccessHandler)
            .failureHandler(myAuthenticationFailHandler);

        http
            .authorizeRequests()
            .antMatchers("/user/*")
            .authenticated()
            .antMatchers("/oauth/token").permitAll()
            .anyRequest()
            .permitAll()
            .and()
            //关闭跨站请求防护
            .csrf().disable();
    }
}
