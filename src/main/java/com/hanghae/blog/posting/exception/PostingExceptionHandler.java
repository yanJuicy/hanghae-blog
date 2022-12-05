package com.hanghae.blog.posting.exception;

import com.hanghae.blog.posting.controller.PostingController;
import com.hanghae.blog.posting.dto.PostingDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

import static com.hanghae.blog.common.exception.ExceptionMessage.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice(assignableTypes = PostingController.class)
public class PostingExceptionHandler {

    @ExceptionHandler({NoSuchElementException.class, IllegalArgumentException.class})
    @ResponseStatus(BAD_REQUEST)
    public PostingDto.Exception handleBadRequest(RuntimeException e) {
        if (e.getMessage().equals(NO_EXIST_POSTING_EXCEPTION_MSG.getMsg())) {
            return new PostingDto.Exception(NO_EXIST_POSTING_EXCEPTION_MSG);
        } else if (e.getMessage().equals(WRONG_PASSWORD_EXCEPTION_MSG.getMsg())) {
            return new PostingDto.Exception(WRONG_PASSWORD_EXCEPTION_MSG);
        }
        return null;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public PostingDto.Exception handleServerError(Exception e) {
        return new PostingDto.Exception(INTERNAL_SERVER_ERROR_MSG);
    }


}
