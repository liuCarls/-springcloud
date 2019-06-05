package com.lgx.springmvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) // 启用方法安全设置
public class WebSecurityConfig2 extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;
    /**
     * 自定义的加密算法
     * @return
     */
    @Bean
    public PasswordEncoder myPasswordEncoder() {
        return new MyPasswordEncoder();
    }
    /**
     * 使用Provider定义认证及授权处理类
     * @return
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(myPasswordEncoder()); // 设置密码加密方式
        return authenticationProvider;
    }

    @Autowired
    private DataSource dataSource;
    /**
     * 认证信息管理
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userDetailsService);
        //Method1. 自定义一个AuthenticationProvider
        //不管通过哪种方式，都是通过一个自动注入的
        // AuthenticationManagerBuilder对象来构建AuthenticationManager的。
        auth.userDetailsService(userService);
        auth.authenticationProvider(authenticationProvider());

//       Method2. 基于JDBC验证的支持
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .withDefaultSchema()
                .withUser("user").password("password").roles("USER").and()
                .withUser("admin").password("password").roles("USER", "ADMIN");
//      Method3.  基于LDAP的认证
        auth
                .ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups");

    }
    private static final String KEY = "liuyanzhao.com";
    /**
     * 自定义配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/","/css/**", "/js/**", "/fonts/**","/users").permitAll() // 都可以访问
                .antMatchers("/h2-console/**").permitAll() // 都可以访问
                .antMatchers("/admin/**").hasRole("ADMIN") // 需要相应的角色才能访问
                .antMatchers("/console/**").hasAnyRole("ADMIN","USER") // 需要相应的角色才能访问
                .and()
                .formLogin()   //基于 Form 表单登录验证
                .loginPage("/login").failureUrl("/login?error=true") // 自定义登录界面
                .and().rememberMe().key(KEY) // 启用 remember me, 关闭浏览器后，下次启动，依然是登录状态，记住密码后，我们可以看到浏览器里添加了 一条 cookie
                .and().exceptionHandling().accessDeniedPage("/403");  // 处理异常，拒绝访问就重定向到 403 页面
        http.csrf().ignoringAntMatchers("/h2-console/**"); // 禁用 H2 控制台的 CSRF 防护
        http.headers().frameOptions().sameOrigin()
        .and().logout().logoutSuccessUrl(""); // 允许来自同一来源的H2 控制台的请求
    }
}
