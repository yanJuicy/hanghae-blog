package com.hanghae.blog.member.exception;

import com.hanghae.blog.member.controller.MemberController;
import com.hanghae.blog.posting.dto.PostingDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.hanghae.blog.common.exception.ExceptionMessage.ALREADY_EXIST_MEMBER_EXCEPTION_MSG;
import static com.hanghae.blog.common.exception.ExceptionMessage.BAD_REQUEST_ERROR_MSG;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice(assignableTypes = MemberController.class)
public class MemberExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(BAD_REQUEST)
    public PostingDto.Exception handleBadRequest(RuntimeException e) {
        if (e.getMessage().equals(ALREADY_EXIST_MEMBER_EXCEPTION_MSG.getMsg())) {
            return new PostingDto.Exception(ALREADY_EXIST_MEMBER_EXCEPTION_MSG);
        }

        return new PostingDto.Exception(BAD_REQUEST_ERROR_MSG);
    }

}
