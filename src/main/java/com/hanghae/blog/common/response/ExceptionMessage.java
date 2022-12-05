package com.hanghae.blog.common.response;

import lombok.Getter;

@Getter
public enum ExceptionMessage {

    NO_EXIST_POSTING_EXCEPTION_MSG(400,"해당 포스팅이 존재하지 않습니다."),
    WRONG_PASSWORD_EXCEPTION_MSG(400,"잘못된 비밀번호 값 입니다.");

    private final int status;
    private final String msg;

    ExceptionMessage(final int status, final String msg) {
        this.status = status;
        this.msg = msg;
    }
}
