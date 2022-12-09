package com.hanghae.blog.comment.service;

import com.hanghae.blog.comment.dto.CreateCommentRequestDto;
import com.hanghae.blog.comment.dto.CreateCommentResponseDto;
import com.hanghae.blog.comment.dto.UpdateCommentRequestDto;
import com.hanghae.blog.comment.dto.UpdateCommentResponseDto;
import com.hanghae.blog.comment.entity.Comment;
import com.hanghae.blog.comment.repository.CommentRepository;
import com.hanghae.blog.common.response.ResponseDto;
import com.hanghae.blog.jwt.JwtService;
import com.hanghae.blog.member.entity.Member;
import com.hanghae.blog.member.repository.MemberRepository;
import com.hanghae.blog.posting.entity.Posting;
import com.hanghae.blog.posting.repository.PostingRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

import static com.hanghae.blog.common.exception.ExceptionMessage.NO_EXIST_MEMBER_EXCEPTION_MSG;
import static com.hanghae.blog.common.exception.ExceptionMessage.NO_EXIST_POSTING_EXCEPTION_MSG;
import static com.hanghae.blog.common.exception.ExceptionMessage.WRONG_USER_EXCEPTION_MSG;
import static com.hanghae.blog.common.response.ResponseMessage.CREATE_COMMENT_SUCCESS_MSG;
import static com.hanghae.blog.common.response.ResponseMessage.DELETE_COMMENT_SUCCESS_MSG;
import static com.hanghae.blog.common.response.ResponseMessage.UPDATE_COMMENT_SUCCESS_MSG;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final JwtService jwtService;
    private final PostingRepository postingRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public CreateCommentResponseDto createComment(Long postingId, CreateCommentRequestDto requestDto,
                                                  HttpServletRequest servletRequest) {
        String usernameInToken = getTokenSubject(servletRequest);

        Posting foundPosting = postingRepository.findById(postingId)
                .orElseThrow(() -> new NoSuchElementException(NO_EXIST_POSTING_EXCEPTION_MSG.getMsg()));

        Member foundMember = memberRepository.findByUsername(usernameInToken)
                .orElseThrow(() -> new NoSuchElementException(NO_EXIST_MEMBER_EXCEPTION_MSG.getMsg()));

        Comment comment = new Comment(requestDto.getContent(), foundMember, foundPosting);
        commentRepository.save(comment);
        foundPosting.addComment(comment);

        return new CreateCommentResponseDto(CREATE_COMMENT_SUCCESS_MSG, comment);
    }

    @Transactional
    public UpdateCommentResponseDto updateComment(Long postingId, Long commentId, UpdateCommentRequestDto requestDto,
                                                  HttpServletRequest servletRequest) {
        String usernameInToken = getTokenSubject(servletRequest);

        Comment foundComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException());

        Member foundMember = memberRepository.findByUsername(usernameInToken)
                .orElseThrow(() -> new NoSuchElementException(NO_EXIST_MEMBER_EXCEPTION_MSG.getMsg()));

		Posting foundPosting = postingRepository.findById(postingId)
				.orElseThrow(() -> new NoSuchElementException(NO_EXIST_POSTING_EXCEPTION_MSG.getMsg()));

        if (!foundComment.getUsername().equals(foundMember.getUsername())) {
            throw new IllegalArgumentException(WRONG_USER_EXCEPTION_MSG.getMsg());
        }

        foundComment.update(requestDto);
		foundPosting.addComment(foundComment);

		return new UpdateCommentResponseDto(UPDATE_COMMENT_SUCCESS_MSG, foundComment);
    }

    @Transactional
    public ResponseDto deleteComment(Long commentId, HttpServletRequest servletRequest) {
        String usernameInToken = getTokenSubject(servletRequest);

        Comment foundComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException());

        Member foundMember = memberRepository.findByUsername(usernameInToken)
                .orElseThrow(() -> new NoSuchElementException(NO_EXIST_MEMBER_EXCEPTION_MSG.getMsg()));

        if (!foundComment.getUsername().equals(foundMember.getUsername())) {
            throw new IllegalArgumentException(WRONG_USER_EXCEPTION_MSG.getMsg());
        }

        commentRepository.delete(foundComment);

        return new ResponseDto(DELETE_COMMENT_SUCCESS_MSG);
    }

    private String getTokenSubject(HttpServletRequest servletRequest) {
        Claims claim = jwtService.getTokenClaim(servletRequest);
        String username = claim.getSubject();
        return username;
    }


}
