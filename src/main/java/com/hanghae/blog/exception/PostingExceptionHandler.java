package com.hanghae.blog.exception;

import com.hanghae.blog.controller.PostingController;
import com.hanghae.blog.dto.PostingDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice(assignableTypes = PostingController.class)
public class PostingExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public PostingDto.Exception handleBadRequest(RuntimeException e) {
        return new PostingDto.Exception(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public PostingDto.Exception handleServerError(Exception e) {
        return new PostingDto.Exception(e.getMessage());
    }


}
