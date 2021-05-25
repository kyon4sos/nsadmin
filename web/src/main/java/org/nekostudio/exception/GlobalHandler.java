package org.nekostudio.exception;

import lombok.extern.slf4j.Slf4j;
import org.nekostudio.common.BussineException;
import org.nekostudio.common.JsonResult;
import org.nekostudio.common.ResultEnum;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author neko
 */
@Slf4j
@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    JsonResult argInvalid(MethodArgumentNotValidException e) {
        FieldError err = e.getFieldError();
        if (err != null) {
            log.error("MethodArgumentNotValidException {}\n", e.getMessage());
            return JsonResult.fail(err.getDefaultMessage(), 40000);
        }
        return JsonResult.fail(ResultEnum.ARGS_EXCEPTION);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    JsonResult methodNotSupport(HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException {}\n", e.getMessage());
        return JsonResult.fail(ResultEnum.NO_SUPPORT);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class,UnsatisfiedServletRequestParameterException.class})
    JsonResult notReadAbleHandler(HttpMessageNotReadableException e) {
        log.error("HttpMessageNotReadableException {}\n", e.getMessage());
        return JsonResult.fail(ResultEnum.ARGS_EXCEPTION);
    }


    @ExceptionHandler(BadCredentialsException.class)
    JsonResult handler(BadCredentialsException e) {
        log.error("Exception{} {}\n", e.getClass(), e.getMessage());
        return JsonResult.fail(ResultEnum.WRONG_PASSWORD);
    }
    @ExceptionHandler(BussineException.class)
    JsonResult handler(BussineException e) {
        return JsonResult.fail(e.getMsg(), e.getCode());
    }

    @ExceptionHandler(Exception.class)
    JsonResult handler(Exception e) {
        log.error("Exception{} {}\n", e.getClass(), e.getMessage());
        return JsonResult.fail(e.getMessage());
    }
}