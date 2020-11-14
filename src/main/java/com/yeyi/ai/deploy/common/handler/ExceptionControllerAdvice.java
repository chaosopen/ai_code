package com.yeyi.ai.deploy.common.handler;

import com.yeyi.ai.deploy.common.exception.AiDeployException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
//@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = AiDeployException.class)
    public void exceptionHandler(AiDeployException e) {
        log.error("系统运行异常", e);
    }

}
