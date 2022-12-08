package com.hanghae.blog.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class UpdateCommentRequestDto {

    @NotNull
    private String content;

}
