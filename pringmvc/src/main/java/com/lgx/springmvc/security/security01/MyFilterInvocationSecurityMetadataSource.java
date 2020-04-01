package com.lgx.springmvc.security.security01;

import org.apache.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private static Logger logger = Logger.getLogger(MyFilterInvocationSecurityMetadataSource.class);
    private HashMap<String, Collection<ConfigAttribute>> map = null;
    /**
     * 加载权限表中所有权限
     */
    public void loadResourceDefine() {
        map = new HashMap<String, Collection<ConfigAttribute>>();
//        List<Permission> permissions = permissionMapper.findAll();
        List<Permission> permissions = new ArrayList<>();
        Permission p = new Permission();
        p.setPermissionname("ROLE_ADMIN");
        p.setUrl("/admin");
        permissions.add(p);
        Permission p1 = new Permission();
        p1.setPermissionname("ROLE_USER");
        p1.setUrl("/user");
        permissions.add(p1);
        for (Permission permission : permissions) {
            ConfigAttribute cfg = new SecurityConfig(permission.getPermissionname());
            List<ConfigAttribute> list = new ArrayList<>();
            list.add(cfg);
            map.put(permission.getUrl(), list);
        }
    }
    /**
     * 此方法是为了判定用户请求的url 是否在权限表中，
     * 如果在权限表中，则返回给 decide 方法，用来判定用户
     * 是否有此权限。如果不在权限表中则放行。
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        logger.debug("-----------------getAttributes-----------------------");
        if (map == null) {
            loadResourceDefine();
        }
        // object 中包含用户请求的request的信息
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        for (Map.Entry<String, Collection<ConfigAttribute>> entry : map.entrySet()) {
            String url = entry.getKey();
            if (new AntPathRequestMatcher(url).matches(request)) {
                return map.get(url);
            }
        }
        // return null时不拦截URL
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
