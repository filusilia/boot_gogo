package com.alice.boot.common.exception;

import com.alice.boot.common.enums.ResultCode;
import com.alice.boot.common.result.ResultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author alice on 2019/12/19 0028.
 * @version 1.0
 * @since 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    setValue(format.parse(text));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @ExceptionHandler(value = {NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultResponse noHandlerFoundException(NoHandlerFoundException ex) {
        return ResultResponse.failed(ResultCode.NOT_FOUND);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultResponse constraintViolationException(ConstraintViolationException ex) {
        return ResultResponse.failed(ResultCode.BAD_REQUEST);
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResultResponse methodArgumentNotValidException(HttpRequestMethodNotSupportedException ex) {
        return ResultResponse.failed(ResultCode.METHOD_NOT_SUPPORT);
    }

    @ExceptionHandler(value = {BaseBusinessException.class})
    public ResultResponse BusinessExceptionHandler(BaseBusinessException e) {
        return ResultResponse.init(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResultResponse defaultErrorHandler(HttpServletRequest request, Exception exception) throws Exception {
        return ResultResponse.failed(ResultCode.SERVER_ERROR);
    }
}
