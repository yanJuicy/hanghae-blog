package com.hanghae.blog.common.response;

import lombok.Getter;

@Getter
public enum ResponseMessage {

    // posting
    READ_POSTING_SUCCESS_MSG(200, "포스팅 정보 조회 성공"),
    CREATE_POSTING_SUCCESS_MSG(201, "포스팅 정보 조회 성공"),
    UPDATE_POSTING_SUCCESS_MSG(200, "포스팅 업데이트 성공"),
    DELETE_POSTING_SUCCESS_MSG(200, "포스팅 삭제 성공"),

    // member
    LOGIN_MEMBER_SUCCESS_MSG(200, "로그인 성공"),
    CREATE_MEMBER_SUCCESS_MSG(201, "회원 등록 성공");


    private final int status;
    private final String msg;
    ResponseMessage(final int status, final String msg) {
        this.status = status;
        this.msg = msg;
    }
}
