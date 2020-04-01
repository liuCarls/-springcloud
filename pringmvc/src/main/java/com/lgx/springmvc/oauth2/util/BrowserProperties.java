package com.lgx.springmvc.oauth2.util;

/**
 * browser(浏览器)配置文件里的： lgx.security.browser 属性类
 */
public class BrowserProperties {
    /**
     *  loginPage 默认值  是login.html
     */
    private String loginPage = "/browser-login.html";

    /**
     * 默认 返回 json 类型
     */
    private LoginType loginType = LoginType.JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
