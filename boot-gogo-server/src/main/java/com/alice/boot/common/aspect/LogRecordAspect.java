package com.alice.boot.common.aspect;

import com.alice.boot.common.enums.ResultCode;
import com.alice.boot.common.result.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author alice on 2020/09/01
 * @version 1.0
 * @since 1.0
 */
@Aspect
@Configuration
@Slf4j
public class LogRecordAspect {

    /**
     * 定义切点Pointcut
     */
    @Pointcut("execution(* com.alice.boot.controller..*.*(..))")
    public void excudeService() {
    }

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//
//        if (log.isDebugEnabled()) {
//            log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
//                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
//        }
//        try {
//            Object result = joinPoint.proceed();
//            if (log.isDebugEnabled()) {
//                log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
//                        joinPoint.getSignature().getName(), result);
//            }
//            return result;
//        } catch (IllegalArgumentException e) {
//            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
//                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
//
//            throw e;
//        }
        log.info("请求开始接收 controller:`{}` , 请求参数 = {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        // result的值就是被拦截方法的返回值
        Object result = joinPoint.proceed();
        Signature signature = joinPoint.getSignature();
        if (result instanceof ResultResponse) {
            ResultResponse<?> response = (ResultResponse<?>) result;
            if (Objects.equals(response.getCode(), ResultCode.DANGER_REQUEST.getCode())) {
                log.error("警告，产生了一条危险请求");
            }
        }
        log.info("controller: `{}`请求结束,返回值是:{}", signature.getName(), result.toString());
        return result;
    }
}
