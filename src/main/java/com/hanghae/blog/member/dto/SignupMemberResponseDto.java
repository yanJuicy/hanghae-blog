package com.hanghae.blog.member.dto;

import com.hanghae.blog.common.response.ResponseDto;
import com.hanghae.blog.common.response.ResponseMessage;
import lombok.Getter;

@Getter
public class SignupMemberResponseDto extends ResponseDto {

    public SignupMemberResponseDto(ResponseMessage responseMessage) {
        super(responseMessage);
    }
}
