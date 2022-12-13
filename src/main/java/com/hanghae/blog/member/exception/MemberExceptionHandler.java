package com.hanghae.blog.member.exception;

import com.hanghae.blog.common.exception.ExceptionResponseDto;
import com.hanghae.blog.member.controller.MemberController;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

import static com.hanghae.blog.common.exception.ExceptionMessage.ALREADY_EXIST_MEMBER_EXCEPTION_MSG;
import static com.hanghae.blog.common.exception.ExceptionMessage.BAD_REQUEST_ERROR_MSG;
import static com.hanghae.blog.common.exception.ExceptionMessage.NO_EXIST_MEMBER_EXCEPTION_MSG;
import static com.hanghae.blog.common.exception.ExceptionMessage.WRONG_ADMIN_TOKEN_EXCEPTION_MSG;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = MemberController.class)
public class MemberExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class, NoSuchElementException.class})
    public ExceptionResponseDto handleBadRequest(RuntimeException e) {
        if (e.getMessage().equals(ALREADY_EXIST_MEMBER_EXCEPTION_MSG.getMsg())) {
            return new ExceptionResponseDto(ALREADY_EXIST_MEMBER_EXCEPTION_MSG);
        } else if(e.getMessage().equals(NO_EXIST_MEMBER_EXCEPTION_MSG.getMsg())) {
            return new ExceptionResponseDto(NO_EXIST_MEMBER_EXCEPTION_MSG);
        } else if (e.getMessage().equals(WRONG_ADMIN_TOKEN_EXCEPTION_MSG.getMsg())) {
            return new ExceptionResponseDto(WRONG_ADMIN_TOKEN_EXCEPTION_MSG);
        }

        return new ExceptionResponseDto(BAD_REQUEST_ERROR_MSG);
    }

}
