package com.alice.nsgogo.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Nano on 2018/5/29.
 * @version 1.0
 * @since 1.0
 */
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
//        if (LoginType.JSON.equals(securityProperties.getLoginType())) {
//            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().write(objectMapper.writeValueAsString(ResultUtil.success(authentication.getName())));
//
//        } else {
//            super.onAuthenticationSuccess(request, response, authentication);
//        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
