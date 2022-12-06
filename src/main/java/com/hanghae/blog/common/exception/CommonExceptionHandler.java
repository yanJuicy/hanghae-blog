package com.hanghae.blog.common.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.hanghae.blog.common.exception.ExceptionMessage.INTERNAL_SERVER_ERROR_MSG;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResponseDto handleBadRequest(Exception e) {
        return new ExceptionResponseDto(e.getMessage(), BAD_REQUEST.value());
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionResponseDto handleServerError(Exception e) {
        return new ExceptionResponseDto(INTERNAL_SERVER_ERROR_MSG);
    }

}
