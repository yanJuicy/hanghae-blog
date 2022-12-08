package com.hanghae.blog.comment.dto;

import com.hanghae.blog.comment.entity.Comment;
import com.hanghae.blog.common.response.ResponseDto;
import com.hanghae.blog.common.response.ResponseMessage;

public class UpdateCommentResponseDto extends ResponseDto {

    private Object comment;

    public UpdateCommentResponseDto(ResponseMessage responseMessage, Comment comment) {
        super(responseMessage);
        this.comment = comment;
    }
}
