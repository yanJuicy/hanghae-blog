package com.hanghae.blog.posting.mapper;

import com.hanghae.blog.common.response.GenericResponseDto;
import com.hanghae.blog.member.entity.Member;
import com.hanghae.blog.posting.dto.RequestCreatePostingDto;
import com.hanghae.blog.posting.dto.ResponseCreatePostingDto;
import com.hanghae.blog.posting.entity.Posting;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static com.hanghae.blog.common.response.ResponseMessage.CREATE_POSTING_SUCCESS_MSG;

@Component
public class PostingMapper {

	public GenericResponseDto<ResponseCreatePostingDto> toResponse(Posting posting) {
		ResponseCreatePostingDto response = ResponseCreatePostingDto.builder()
				.id(posting.getId())
				.title(posting.getTitle())
				.writer(posting.getWriter())
				.contents(posting.getContents())
				.lastModifiedAt(posting.getLastModifiedAt())
				.commentList(new ArrayList<>())
				.build();

		return new GenericResponseDto<>(CREATE_POSTING_SUCCESS_MSG, response);
	}

	public Posting toPosting(RequestCreatePostingDto requestDto, Member member) {
		return new Posting(requestDto.getTitle(),
				requestDto.getWriter(),
				requestDto.getContents(),
				requestDto.getPassword(),
				member);
	}
}
