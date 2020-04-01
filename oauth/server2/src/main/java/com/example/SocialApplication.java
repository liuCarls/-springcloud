/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootApplication
@Controller
// 认证服务器
public class SocialApplication {
	@RequestMapping({ "/home", "/" })
	public String home(Principal principal) {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("name", principal.getName()+":SERVER");
		return "index";
	}

	@RequestMapping({ "/login" })
	public String login() {
		return "login";
	}


//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// @formatter:off
//		http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login**", "/webjars/**").permitAll().anyRequest()
//				.authenticated().and().exceptionHandling()
//				// 没授权的用户将会跳转到此页面
//				.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"));
//				// 增加过滤链，处理要认证的链接
//		// @formatter:on
//	}

//	@Configuration
//	@EnableWebSecurity  //只有这个注释才使 formLogin等生效
//	protected class WebSecurityConfig extends WebSecurityConfigurerAdapter {
////		@Override
////		@Bean //分享到oauth2
////		public AuthenticationManager authenticationManagerBean() throws Exception {
////			return super.authenticationManagerBean();
////		}
//
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			http.authorizeRequests()
//				.antMatchers( "/login", "/webjars/**", "/oauth/**").permitAll()
//				.anyRequest().authenticated().and().exceptionHandling()
//					.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
////				.and().formLogin() //默认是index.html
//				.and().formLogin().loginPage("/login")
//				.and().csrf().disable();
//
//
//		}
//	}

//	@Configuration
//	protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//		@Override
//		public void configure(HttpSecurity http) throws Exception {
//			// @formatter:off
////			http.authorizeRequests()
////				.antMatchers( "/login", "/webjars/**").permitAll()
////					.and().formLogin().loginPage("/login");
//			http.requestMatchers()
//					.antMatchers("/user").and()
//					.authorizeRequests().antMatchers("/user").authenticated();
//			// @formatter:on
//
//		}
//	}

	public static void main(String[] args) {
		SpringApplication.run(SocialApplication.class, args);
	}

}
