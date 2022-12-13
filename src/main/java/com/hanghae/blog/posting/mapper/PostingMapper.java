package com.hanghae.blog.posting.mapper;

import com.hanghae.blog.member.entity.Member;
import com.hanghae.blog.posting.dto.RequestCreatePostingDto;
import com.hanghae.blog.posting.dto.ResponsePostingDto;
import com.hanghae.blog.posting.entity.Posting;
import org.springframework.stereotype.Component;

@Component
public class PostingMapper {

	public ResponsePostingDto toResponse(Posting posting) {
		return ResponsePostingDto.builder()
				.id(posting.getId())
				.title(posting.getTitle())
				.writer(posting.getWriter())
				.contents(posting.getContents())
				.lastModifiedAt(posting.getLastModifiedAt())
				.commentList(posting.getCommentList())
				.build();
	}

	public Posting toPosting(RequestCreatePostingDto requestDto, Member member) {
		return new Posting(requestDto.getTitle(),
				requestDto.getWriter(),
				requestDto.getContents(),
				requestDto.getPassword(),
				member);
	}

}
