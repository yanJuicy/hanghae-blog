package com.hanghae.blog.comment.mapper;

import com.hanghae.blog.comment.dto.CreateCommentRequestDto;
import com.hanghae.blog.comment.dto.ResponseCommentDto;
import com.hanghae.blog.comment.entity.Comment;
import com.hanghae.blog.member.entity.Member;
import com.hanghae.blog.posting.entity.Posting;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

	public ResponseCommentDto toResponse(Comment comment) {
		return new ResponseCommentDto(comment.getId(),
				comment.getContent(),
				comment.getCreatedAt(),
				comment.getLastModifiedAt(),
				comment.getMember().getUsername());
	}

	public Comment toComment(CreateCommentRequestDto requestDto, Member member, Posting posting) {
		return new Comment(requestDto.getContent(), member, posting);
	}


}
