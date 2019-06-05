package com.lgx.springmvc.security;

import com.lgx.springmvc.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDetails loadUserByUsername(String username) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        SecurityUser securityUser = null;
        //模拟admin用户/权限
        if("admin".equalsIgnoreCase(username)){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        authorities.add(new SimpleGrantedAuthority("tester"));
            securityUser = new SecurityUser("admin","admin",authorities);
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            securityUser = new SecurityUser("user","123456",authorities);
        }
        System.out.println("------------loadUserByUsername------------"+username);
        return securityUser;
    }

    @Override
    public void userRegister(String username, String password) {
        User user  = new User();
//        user.setUsername(passwordEncoder.encode(username));
//        user.setPassword(password);
//        userMapper.insert(user);
//        User rtnUser =userMapper.selectByUsername(username);
        //注册成功默认给用户的角色是user
//        roleMapper.insertUserRole(rtnUser.getId(), 2);
    }
}
