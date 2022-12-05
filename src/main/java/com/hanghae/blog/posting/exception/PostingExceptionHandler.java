package com.hanghae.blog.posting.exception;

import com.hanghae.blog.posting.controller.PostingController;
import com.hanghae.blog.posting.dto.PostingDto;
import com.hanghae.blog.common.custom.NotEnoughArgumentException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice(assignableTypes = PostingController.class)
public class PostingExceptionHandler {

    @ExceptionHandler({NoSuchElementException.class, IllegalArgumentException.class, NotEnoughArgumentException.class})
    @ResponseStatus(BAD_REQUEST)
    public PostingDto.Exception handleBadRequest(RuntimeException e) {
        return new PostingDto.Exception(BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public PostingDto.Exception handleServerError(Exception e) {
        return new PostingDto.Exception(INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }


}
