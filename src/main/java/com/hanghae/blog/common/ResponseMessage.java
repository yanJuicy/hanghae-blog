package com.hanghae.blog.common;

import lombok.Getter;

@Getter
public enum ResponseMessage {
    READ_POSTING(200, "포스팅 정보 조회 성공"),
    CREATE_POSTING(201, "포스팅 정보 조회 성공"),
    UPDATE_POSTING(200, "포스팅 업데이트 성공"),
    DELETE_POSTING(200, "포스팅 삭제 성공");

    private final int status;
    private final String msg;
    ResponseMessage(final int status, final String msg) {
        this.status = status;
        this.msg = msg;
    }
}
