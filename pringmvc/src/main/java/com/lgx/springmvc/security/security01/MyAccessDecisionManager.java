package com.lgx.springmvc.security.security01;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

@Service
public class MyAccessDecisionManager implements AccessDecisionManager {
    private static Logger logger = Logger.getLogger(MyAccessDecisionManager.class);
    /**
     * decide 方法是判定是否拥有权限的决策方法，authentication是CustomUserService
     * 中循环添加到 GrantedAuthority 对象中的权限信息集合,object 包含客户端发起的请求的requset信息，
     * 可转换为 HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
     * configAttributes为MyFilterInvocationSecurityMetadataSource的getAttributes(Object object)
     * 这个方法返回的结果.
     * @param authentication 表示用户信息，有
     * @param object 表示当前请求
     * @param configAttributes 表示资源需要的权限
     *
     */
    @Override
    public void decide(Authentication authentication, Object object,
                       Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        logger.debug("--------decide------------");
        if(null== configAttributes || configAttributes.size() <=0) {
            return;
        }
        ConfigAttribute c;
        String needRole;
        for(Iterator<ConfigAttribute> iter = configAttributes.iterator(); iter.hasNext(); ) {
            c = iter.next();
            needRole = c.getAttribute();
            for(GrantedAuthority ga : authentication.getAuthorities()) {
                //authentication 为在注释1 中循环添加到 GrantedAuthority 对象中的权限信息集合
                if(needRole.trim().equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("no right");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
