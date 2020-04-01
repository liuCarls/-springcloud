package com.lgx.springmvc.security.security01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {
    private static Logger logger = LoggerFactory.getLogger(AuthenticationAccessDeniedHandler.class);
    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse resp,
                       AccessDeniedException e) throws IOException, ServletException {
        System.out.println("-------------handle------------------");
//        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
//        resp.setContentType("application/json;charset=UTF-8");
//        PrintWriter out = resp.getWriter();
//        out.write("{\"status\":\"error\",\"msg\":\"权限不足，请联系管理员!\"}");
//        out.flush();
//        out.close();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            logger.info("User '" + auth.getName()
                    + "' attempted to access the protected URL: "
                    + httpServletRequest.getRequestURI());
        }
        resp.sendRedirect(httpServletRequest.getContextPath() + "/403");

    }
}
