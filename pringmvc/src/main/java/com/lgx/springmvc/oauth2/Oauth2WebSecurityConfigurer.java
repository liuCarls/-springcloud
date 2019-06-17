package com.lgx.springmvc.oauth2;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置,即身份认证,
 */
@Configuration
@EnableWebSecurity
//@EnableOAuth2Sso
public class Oauth2WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.requestMatchers()
//                .antMatchers("/login", "/oauth/authorize/oauth/logout")
////                .and().authorizeRequests().anyRequest().authenticated()
//                .antMatchers("/user/**")
//                .and().formLogin().permitAll();

//        http.requestMatchers()与http.authorizeRequests()的区别？
//        http.authorizeRequests().antMatchers("/user/**").hasRole("USER")
//                .antMatchers("/admin/**").hasAnyRole("ADMIN")
//                .anyRequest().authenticated()
//                .and().csrf().disable();

        //登陆完成之后应该有一个token,这个token是哪一种？在哪里产生昵？
        http.formLogin().loginPage("/login")
//                loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password").permitAll()
//              .defaultSuccessUrl("/hello")  // 登录页，重定向的次数过多，Why?/hello没有放开权限
//                .and().logout().invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/loginPage").permitAll()
        ;

        //在这里应该添加oauthd的token权限，应该在
        //  /oauth/authorize
        //  /oauth/token
        //  /oauth/check_token
        //  /oauth/confirm_access
        //  /oauth/error
        http.requestMatchers()
                .antMatchers("/login", "/oauth/authorize")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN")
                .and().withUser("user").password("123456").roles("USER");
    }

}
