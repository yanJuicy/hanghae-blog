package com.hanghae.blog.posting.mapper;

import com.hanghae.blog.comment.dto.ResponseCommentDto;
import com.hanghae.blog.comment.entity.Comment;
import com.hanghae.blog.comment.mapper.CommentMapper;
import com.hanghae.blog.member.entity.Member;
import com.hanghae.blog.posting.dto.RequestCreatePostingDto;
import com.hanghae.blog.posting.dto.ResponsePostingDto;
import com.hanghae.blog.posting.entity.Posting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PostingMapper {

	private final CommentMapper commentMapper;

	public ResponsePostingDto toResponse(Posting posting) {
		List<Comment> commentList = posting.getCommentList();
		List<ResponseCommentDto> responseCommentDtoList = commentList.stream()
				.map(c -> commentMapper.toResponse(c))
				.collect(Collectors.toList());

		return ResponsePostingDto.builder()
				.id(posting.getId())
				.title(posting.getTitle())
				.writer(posting.getWriter())
				.contents(posting.getContents())
				.lastModifiedAt(posting.getLastModifiedAt())
				.commentList(responseCommentDtoList)
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
