package com.hanghae.blog.member.dto;

import com.hanghae.blog.common.response.ResponseDto;
import com.hanghae.blog.common.response.ResponseMessage;
import lombok.Getter;

@Getter
public class JoinMemberResponseDto extends ResponseDto {

    public JoinMemberResponseDto(ResponseMessage responseMessage) {
        super(responseMessage);
    }
}
