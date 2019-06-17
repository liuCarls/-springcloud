package com.lgx.springmvc.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lgx.springmvc.oauth2.util.LoginType;
import com.lgx.springmvc.oauth2.util.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFailHandler_oauth2 extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * json 转换工具类
     */
    private ObjectMapper objectMapper;

//    @Autowired
//    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        //如果是json 格式
        if (LoginType.JSON.equals("JSON")){
            logger.info("登录失败");
            //设置状态码
            response.setStatus(500);
            response.setContentType("application/json;charset=UTF-8");
            //将 登录失败 信息打包成json格式返回
//            response.getWriter().write(JSON.toJSONString(ServerResponse.createByErrorMessage(exception.getMessage())));
            response.getWriter().write(objectMapper.writeValueAsString(exception));

        } else {
            //如果不是json格式，返回view
            super.onAuthenticationFailure(request,response,exception);
        }
    }
}
