package com.hanghae.blog.comment.controller;

import com.hanghae.blog.comment.dto.CreateCommentRequestDto;
import com.hanghae.blog.comment.dto.CreateCommentResponseDto;
import com.hanghae.blog.comment.dto.UpdateCommentRequestDto;
import com.hanghae.blog.comment.dto.UpdateCommentResponseDto;
import com.hanghae.blog.comment.service.CommentService;
import com.hanghae.blog.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("/api/postings/{postingId}/comments")
@RestController
public class CommentController {

	private final CommentService commentService;

	@PostMapping
	public CreateCommentResponseDto addComment(@PathVariable Long postingId, @RequestBody CreateCommentRequestDto requestDto,
											   HttpServletRequest servletRequest) {
		return commentService.createComment(postingId, requestDto, servletRequest);
	}

	@PutMapping("/{commentId}")
	public UpdateCommentResponseDto updateComment(@PathVariable Long postingId, @PathVariable Long commentId,
												  @RequestBody UpdateCommentRequestDto requestDto,
												  HttpServletRequest servletRequest) {
		return commentService.updateComment(postingId, commentId, requestDto, servletRequest);
	}

	@DeleteMapping("/{commentId}")
	public ResponseDto deleteComment(@PathVariable Long commentId, HttpServletRequest servletRequest) {
		return commentService.deleteComment(commentId, servletRequest);
	}

}
