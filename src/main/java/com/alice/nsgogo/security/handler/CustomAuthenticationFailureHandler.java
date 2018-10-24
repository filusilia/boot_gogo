package com.alice.nsgogo.security.handler;

import com.alice.nsgogo.enums.ResultCode;
import com.alice.nsgogo.result.ResultResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Nano on 2018/5/25.
 * @version 1.0
 * @since 1.0
 */
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        if (exception instanceof BadCredentialsException) {
            response.getWriter().write(objectMapper.writeValueAsString(ResultResponse.failed(ResultCode.OAUTH_ERROR)));
        }
        super.onAuthenticationFailure(request, response, exception);
    }
}
