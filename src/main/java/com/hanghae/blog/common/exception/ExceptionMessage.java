package com.hanghae.blog.common.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage {

    // common
    INTERNAL_SERVER_ERROR_MSG(500,"서버 에러입니다."),
    BAD_REQUEST_ERROR_MSG(400,"잘못된 사용자 요청입니다."),
    WRONG_PASSWORD_EXCEPTION_MSG(400,"비밀번호가 다릅니다."),
    WRONG_JWT_EXCEPTION_MSG(401,"잘못된 토큰 요청입니다."),

    // posting
    NO_EXIST_POSTING_EXCEPTION_MSG(400,"해당 포스팅이 존재하지 않습니다."),

    // member
    WRONG_JOIN_USERNAME_EXCEPTION(400, "username은 최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9) 입니다."),
    WRONG_JOIN_PASSWORD_EXCEPTION(400, "password는 최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9) 입니다."),
    NO_EXIST_MEMBER_EXCEPTION_MSG(400,"등록된 사용자가 없습니다."),
    WRONG_ADMIN_TOKEN_EXCEPTION_MSG(400,"잘못된 관리자 암호입니다."),
    WRONG_USER_EXCEPTION_MSG(400,"작성한 사용자만 삭제/수정할 수 있습니다."),
    ALREADY_EXIST_MEMBER_EXCEPTION_MSG(400, "이미 회원가입한 유저입니다.");

    private final int status;
    private final String msg;

    ExceptionMessage(final int status, final String msg) {
        this.status = status;
        this.msg = msg;
    }
}
