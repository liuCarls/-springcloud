package com.lgx.springmvc.security;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserDetailService extends UserDetailsService {
    void userRegister(String username, String password);
}
