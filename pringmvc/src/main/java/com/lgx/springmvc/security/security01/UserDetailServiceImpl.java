package com.lgx.springmvc.security.security01;

//import com.lgx.springmvc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements IUserDetailService {
    @Autowired
    PasswordEncoder myPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        UserDetailImpl securityUser = null;
        //模拟admin用户/权限
        if("admin".equalsIgnoreCase(username)){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        authorities.add(new SimpleGrantedAuthority("tester"));
            securityUser = new UserDetailImpl("admin","admin",authorities);
        } else if("user".equalsIgnoreCase(username)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            securityUser = new UserDetailImpl("user","123456",authorities);
//            用户名+密码+可用？+没过期？+授权过期？+不被锁？+用户权限
            new User(username,"12345",
                    true,true,true,true,
                    AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        } else {
            throw new RuntimeException("登录账号不存在");
        }
        System.out.println("------------loadUserByUsername------------"+username);
        return securityUser;
    }

    @Override
    public void userRegister(String username, String password) {
        UserDetailImpl user  = new UserDetailImpl();
        user.setUsername(myPasswordEncoder.encode(username));
        user.setPassword(password);
//        userMapper.insert(user);
        //注册成功默认给用户的角色是user
//        User rtnUser =userMapper.selectByUsername(username);
//        roleMapper.insertUserRole(rtnUser.getId(), 2);
    }
}
