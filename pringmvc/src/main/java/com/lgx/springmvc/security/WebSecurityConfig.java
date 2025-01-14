package com.lgx.springmvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

//import org.springframework.security.core.userdetails.User;

/**
 * Spring Security的配置实际上也包含了创建过滤器与注册过滤器两个过程
 *
 *
 * 当我们在一个类上添加@EnableWebSecurity注解后，
 * Spring Security会自动帮助我们创建一个名字为的
 * springSecurityFilterChain过滤器。这个过滤器实际上只
 * 是Spring Security框架验证请求的一个入口，
 * 到底如何验证请求实际上是要依赖于我们如何配置Spring Security。
 * 配置的方式就是通过FormLoginConfigurer、HttpBasicConfigurer等这样的
 * SecurityConfigurerAdapter的子类实现的，
 * SecurityConfigurerAdapter是的SecurityConfigurer接口抽象子类。
 * 通过HttpSecurity.getOrApply--->apply方法将configurer放入一
 * 个LinkedHashMap的属性configurers中，同时会调用每一个
 * SecurityConfigurer的configure方法，在这个方法里主要就
 * 是在HttpSecurity对象中，添加一个对应的验证过滤器。
 * 可以认为通过and()分割后的每段配置，实际上都是在HttpSecuirty中添加一个过滤器。
 *
 *
 * 当然并不是每个SecurityConfigurer都是通过这种方式来创建过滤器的，
 * 例如FormLoginConfigurer就直接在构造方法中来创建一个类型为
 * UsernamePasswordAuthenticationFilter的过滤器
 *
 */
//@Configuration
//@EnableWebSecurity
//@Order(1) //配置多个 HttpSecurity 实例，就像我们可以在xml文件中配置多个 <http>一样。关键在于多次扩展 WebSecurityConfigurationAdapter

//@EnableAutoConfiguration
//  启用方法级别的权限认证
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    IUserDetailService userService;

    @Autowired
    MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;
    @Autowired
    MyAccessDecisionManager myAccessDecisionManager;
    @Autowired
    AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;
    /**
     * 自定义的加密算法
     * @return
     */
    @Bean
    public PasswordEncoder myPasswordEncoder() {
        return new MyPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // ALTOUGH THIS SEEMS LIKE USELESS CODE,
        // ITS REQUIRED TO PREVEND SPRING BOOT AUTO-CONFIGURATION
        return super.authenticationManagerBean();
    }


    /**
     * 身份验证管理生成器:
     * 使用自定义身份验证组件, 重写安全认证，加入密码加密方式
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**  第一种将用户资料存储在内存中   **/
//        auth.inMemoryAuthentication()
//                .withUser("user").password("123456").roles("USER")
//                .and()
//                .withUser("admin").password("admin").roles("USER","ADMIN");

        /**  第三种使用自定义认证，随意搭配方式   **/
        auth.userDetailsService(userService)
                .passwordEncoder(myPasswordEncoder());
    }


    /**
     * WEB安全
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers( "/register","/models/create","/static/**");
    }

    /**
     * HTTP请求安全处理:
     * 设置 HTTP 验证规则
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //表单登录
        http.formLogin().loginPage("/login")
//                loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password").permitAll()
//              .defaultSuccessUrl("/hello")  // 登录页，重定向的次数过多，Why?/hello没有放开权限
//                .and().logout().invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/loginPage").permitAll()
                .and().exceptionHandling().accessDeniedHandler(authenticationAccessDeniedHandler)
        ;


        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
                        o.setAccessDecisionManager(myAccessDecisionManager);
                        return o;
                    }
                })
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .anyRequest().permitAll()
                .and().csrf().disable()

//                .and().formLogin().loginPage("/hello")
//                .usernameParameter("username").passwordParameter("password")
                //  登录页，可以直接找到这个页面吗？还是必须model中转下？
//                .loginPage("/login.html")
                //默认成功url,如果不定义会是什么？
//                .defaultSuccessUrl("/welcome")
//                .failureUrl("/login-error.html")
//                .and().logout().logoutSuccessUrl("/login.html");
;

//        http.authorizeRequests()
//            .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                @Override
//                public <O extends FilterSecurityInterceptor> O postProcess(O o) {
//                    o.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
//                    o.setAccessDecisionManager(myAccessDecisionManager);
//                    return o;
//                }
//            }).and().formLogin().loginPage("/loginPage").
//            loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password").permitAll().
//            failureHandler(new AuthenticationFailureHandler() {
//                @Override
//                public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
//                                                    HttpServletResponse httpServletResponse,
//                                                    AuthenticationException e)
//                                throws IOException, ServletException {
//                    httpServletResponse.setContentType("application/json;charset=utf-8");
//                    PrintWriter out = httpServletResponse.getWriter();
//                    StringBuffer sb = new StringBuffer();
//                    sb.append("{\"status\":\"error\",\"msg\":\"");
//                    if (e instanceof UsernameNotFoundException
//                            || e instanceof BadCredentialsException) {
//                        sb.append("用户名或密码输入错误，登录失败!");
//                    } else {
//                        sb.append("登录失败!");
//                    }
//                    sb.append("\"}");
//                    out.write(sb.toString());
//                    out.flush();
//                    out.close();
//                }
//            }).successHandler(new AuthenticationSuccessHandler() {
//            @Override
//            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//                httpServletResponse.setContentType("application/json;charset=utf-8");
//                PrintWriter out = httpServletResponse.getWriter();
//                String s = "{\"status\":\"success\",\"msg\":\"登陆成功\"}";
//                out.write(s);
//                out.flush();
//                out.close();
//            }
//        }).and().logout().permitAll()
//        .and().csrf().disable()
//        .exceptionHandling().accessDeniedHandler(authenticationAccessDeniedHandler);

//        第二中配置
//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = http.authorizeRequests();
        /**在authorizeRequests.antMatchers()的返回值中可以继续调用hasAnyAuthority()或者hasRole()这2个方法,
         看起来一个是权限,一个是角色,但是仔细研究,你会发现,hasRole只是hasAnyAuthority它的一个简写版
         所以在这个框架中,我认为能控制到角色这一层**/
//        //Specify that URLs require a particular authority.
//        authorizeRequests.antMatchers("/form.do").hasAuthority("ROLL_ADMIN");
//        //Shortcut for specifying URLs require a particular role. If you do not want to have "ROLE_" automatically inserted see hasAuthority(String).
//        authorizeRequests.antMatchers("/form.do").hasRole("ADMIN");
//        authorizeRequests.antMatchers("/form.do").authenticated();//authenticated()必须已经登录了应用
//        authorizeRequests.anyRequest().permitAll();//允许其他请求通过
//        http.formLogin().loginPage("/login.html").loginProcessingUrl("/login.do").defaultSuccessUrl("/index.html")//登录首页，默认验证通过后登录index.html，验证请求/login.do
//                .and().httpBasic()//开启httpBasic认证
//                .and().logout().logoutUrl("/logout.do").logoutSuccessUrl("/login.html")//登出后，跳转login.html
//                .and().rememberMe().tokenValiditySeconds(3600).key("securityKey")//记住我 cookies 中会记录 一小时后失效 60*60
//                .and().csrf().disable();//禁用CSRF保护功能
    }
}
