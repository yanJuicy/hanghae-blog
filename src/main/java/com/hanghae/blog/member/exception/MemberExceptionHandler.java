package com.hanghae.blog.member.exception;

import com.hanghae.blog.member.controller.MemberController;
import com.hanghae.blog.posting.dto.PostingDto;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

import static com.hanghae.blog.common.exception.ExceptionMessage.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = MemberController.class)
public class MemberExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class, NoSuchElementException.class})
    public PostingDto.Exception handleBadRequest(RuntimeException e) {
        if (e.getMessage().equals(ALREADY_EXIST_MEMBER_EXCEPTION_MSG.getMsg())) {
            return new PostingDto.Exception(ALREADY_EXIST_MEMBER_EXCEPTION_MSG);
        } else if(e.getMessage().equals(NO_EXIST_MEMBER_EXCEPTION_MSG.getMsg())) {
            return new PostingDto.Exception(NO_EXIST_MEMBER_EXCEPTION_MSG);
        } else if (e.getMessage().equals(WRONG_ADMIN_TOKEN.getMsg())) {
            return new PostingDto.Exception(WRONG_ADMIN_TOKEN);
        }

        return new PostingDto.Exception(BAD_REQUEST_ERROR_MSG);
    }

}
