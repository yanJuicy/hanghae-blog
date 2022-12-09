package com.hanghae.blog.posting.dto;

import com.hanghae.blog.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCreatePostingDto {

	private Long id;
	private String title;
	private String writer;
	private String contents;
	private LocalDateTime lastModifiedAt;
	private List<Comment> commentList;
}
