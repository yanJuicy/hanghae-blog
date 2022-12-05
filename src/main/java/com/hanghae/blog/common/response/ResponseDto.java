package com.hanghae.blog.common.response;

import lombok.Getter;

@Getter
public class ResponseDto {

    private String msg;
    private int statusCode;

    public ResponseDto(ResponseMessage responseMessage) {
        this.msg = responseMessage.getMsg();
        this.statusCode = responseMessage.getStatus();
    }
}
