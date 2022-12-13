package com.hanghae.blog.posting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdatePostingDto {

	private String title;
	private String writer;
	private String contents;
	private String password;
}
