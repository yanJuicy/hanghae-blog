package com.hanghae.blog.posting.exception;

import com.hanghae.blog.common.exception.ExceptionResponseDto;
import com.hanghae.blog.posting.controller.PostingController;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

import static com.hanghae.blog.common.exception.ExceptionMessage.BAD_REQUEST_ERROR_MSG;
import static com.hanghae.blog.common.exception.ExceptionMessage.NO_EXIST_POSTING_EXCEPTION_MSG;
import static com.hanghae.blog.common.exception.ExceptionMessage.WRONG_PASSWORD_EXCEPTION_MSG;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = PostingController.class)
public class PostingExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({NoSuchElementException.class, IllegalArgumentException.class})
    public ExceptionResponseDto handleBadRequest(RuntimeException e) {
        if (e.getMessage().equals(NO_EXIST_POSTING_EXCEPTION_MSG.getMsg())) {
            return new ExceptionResponseDto(NO_EXIST_POSTING_EXCEPTION_MSG);
        } else if (e.getMessage().equals(WRONG_PASSWORD_EXCEPTION_MSG.getMsg())) {
            return new ExceptionResponseDto(WRONG_PASSWORD_EXCEPTION_MSG);
        }

        return new ExceptionResponseDto(BAD_REQUEST_ERROR_MSG);
    }

}
