package com.alice.boot.common.exception;

import com.alice.boot.common.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author alice on 2019/12/19 0028.
 * @version 1.0
 * @since 1.0
 */
@Component
@Slf4j
public class ExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.error("system error now ,error message:" + ex.getMessage());
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        try {
            PrintWriter writer = response.getWriter();
            writer.write(ResultCode.SERVER_ERROR.getMessage());
            writer.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
