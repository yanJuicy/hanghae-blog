package com.hanghae.blog.comment.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanghae.blog.comment.entity.Comment;
import com.hanghae.blog.common.response.ResponseDto;
import com.hanghae.blog.common.response.ResponseMessage;
import lombok.Getter;

@Getter
public class CreateCommentResponseDto extends ResponseDto {

    private Object data;

    public CreateCommentResponseDto(ResponseMessage responseMessage, Comment comment) {
        super(responseMessage);
        data = comment;

    }
}
