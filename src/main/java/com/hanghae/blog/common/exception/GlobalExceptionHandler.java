package com.hanghae.blog.common.exception;

import com.hanghae.blog.posting.dto.PostingDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.hanghae.blog.common.exception.ExceptionMessage.INTERNAL_SERVER_ERROR_MSG;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public PostingDto.Exception handleServerError(Exception e) {
        return new PostingDto.Exception(INTERNAL_SERVER_ERROR_MSG);
    }

}
