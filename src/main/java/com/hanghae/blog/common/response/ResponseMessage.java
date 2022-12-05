package com.hanghae.blog.common.response;

import lombok.Getter;

@Getter
public enum ResponseMessage {

    // posting
    READ_POSTING(200, "포스팅 정보 조회 성공"),
    CREATE_POSTING(201, "포스팅 정보 조회 성공"),
    UPDATE_POSTING(200, "포스팅 업데이트 성공"),
    DELETE_POSTING(200, "포스팅 삭제 성공"),

    // member
    CREATE_MEMBER(201, "회원 등록 성공");

    private final int status;
    private final String msg;
    ResponseMessage(final int status, final String msg) {
        this.status = status;
        this.msg = msg;
    }
}
