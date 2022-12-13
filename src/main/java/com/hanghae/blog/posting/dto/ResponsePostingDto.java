package com.hanghae.blog.posting.dto;

import com.hanghae.blog.comment.dto.ResponseCommentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class ResponsePostingDto {

	private Long id;
	private String title;
	private String writer;
	private String contents;
	private LocalDateTime lastModifiedAt;
	private List<ResponseCommentDto> commentList;

}
