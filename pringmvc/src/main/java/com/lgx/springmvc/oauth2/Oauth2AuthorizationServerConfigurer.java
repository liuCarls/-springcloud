package com.lgx.springmvc.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 *
 *
 * 授权服务器: Oauth2Config
 * 一般来讲，认证服务器是第三方提供的服务,
 * 认证服务中，我们获取到ClientDetailsServiceConfigurer
 * 并设置clientId和secret还有令牌有效期，
 * 还有支持的授权模式，还有用户权限范围
 */
@Configuration
@EnableAuthorizationServer
public class Oauth2AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;
    /**
     *  配置token获取合验证时的策略
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//        super.configure(security);
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    /**
     * 客户端配置（给谁发令牌）
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("SampleClientId") //客户端ID
                .secret("secret") //5.0以上版本需要加密密码
                //有效时间 2小时
                .accessTokenValiditySeconds(72000)
                //密码授权模式和刷新令牌 "refresh_token","password",
                .authorizedGrantTypes("authorization_code")
                .scopes("user_info")
                .autoApprove(true)  //用户不会被重定向到授权的页面，也不需要手动给请求授权
        ;
//                .scopes( "all"); //授权用户的操作权限
    }

    /**
     * 配置tokenStore
     * 作用：？？？
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authenticationManager(authenticationManager)
//                .userDetailsService(userDetailsService);

        endpoints.authenticationManager(authenticationManager);
//                .tokenStore(memoryTokenStore()); //保存token的方式。
    }

    /**
     * 使用最基本的InMemoryTokenStore生成token
     */
//    @Bean
//    public TokenStore memoryTokenStore() {
//        return new InMemoryTokenStore();
//    }

}

