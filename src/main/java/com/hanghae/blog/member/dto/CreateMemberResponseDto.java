package com.hanghae.blog.member.dto;

import com.hanghae.blog.common.response.ResponseDto;
import com.hanghae.blog.common.response.ResponseMessage;
import lombok.Getter;

@Getter
public class CreateMemberResponseDto extends ResponseDto {

    public CreateMemberResponseDto(ResponseMessage responseMessage) {
        super(responseMessage);
    }
}
