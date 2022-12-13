package com.hanghae.blog.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ResponseCommentDto {

	private Long id;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime lastModifiedAt;
	private String username;


}
