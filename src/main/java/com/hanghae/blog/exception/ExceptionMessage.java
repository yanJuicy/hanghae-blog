package com.hanghae.blog.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    NO_EXIST_POSTING_EXCEPTION_MSG("해당 포스팅이 존재하지 않습니다."),
    WRONG_PASSWORD_EXCEPTION_MSG("잘못된 비밀번호 값 입니다.");

    private final String message;
}
