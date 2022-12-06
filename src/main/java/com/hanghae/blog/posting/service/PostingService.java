package com.hanghae.blog.posting.service;

import com.hanghae.blog.common.exception.custom.IllegalJwtException;
import com.hanghae.blog.jwt.JwtUtil;
import com.hanghae.blog.member.entity.Member;
import com.hanghae.blog.member.service.MemberService;
import com.hanghae.blog.posting.dto.PostingDto;
import com.hanghae.blog.posting.entity.Posting;
import com.hanghae.blog.posting.repository.PostingRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.hanghae.blog.common.exception.ExceptionMessage.NO_EXIST_POSTING_EXCEPTION_MSG;
import static com.hanghae.blog.common.exception.ExceptionMessage.WRONG_JWT_EXCEPTION_MSG;
import static com.hanghae.blog.common.exception.ExceptionMessage.WRONG_PASSWORD_EXCEPTION_MSG;
import static com.hanghae.blog.common.response.ResponseMessage.CREATE_POSTING_SUCCESS_MSG;
import static com.hanghae.blog.common.response.ResponseMessage.READ_POSTING_SUCCESS_MSG;
import static com.hanghae.blog.common.response.ResponseMessage.UPDATE_POSTING_SUCCESS_MSG;

@RequiredArgsConstructor
@Service
public class PostingService {
    private final PostingRepository postingRepository;
    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    public List<PostingDto.Response> findAll() {
        List<Posting> postingList = postingRepository.findByOrderByCreatedAtDesc();
        List<PostingDto.Response> responseList = postingList.stream()
                .map(e -> new PostingDto.Response(READ_POSTING_SUCCESS_MSG, new PostingDto.Data(e)))
                .collect(Collectors.toList());
        return responseList;
    }

    public PostingDto.Response findOne(Long id) {
        Posting foundPosting = postingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(NO_EXIST_POSTING_EXCEPTION_MSG.getMsg()));
        return new PostingDto.Response(READ_POSTING_SUCCESS_MSG, new PostingDto.Data(foundPosting));
    }

    @Transactional
    public PostingDto.Response create(PostingDto.Request requestDto, HttpServletRequest servletRequest) {
        String usernameInToken = getTokenSubject(servletRequest);

        Member member = memberService.findMember(usernameInToken);

        Posting posting = Posting.builder()
                .title(requestDto.getTitle())
                .writer(requestDto.getWriter())
                .contents(requestDto.getContents())
                .password(requestDto.getPassword())
                .memberId(member)
                .build();
        Posting savedPosting = postingRepository.save(posting);
        return new PostingDto.Response(CREATE_POSTING_SUCCESS_MSG, new PostingDto.Data(savedPosting));
    }

    @Transactional
    public PostingDto.Response update(Long id, PostingDto.Request requestDto, HttpServletRequest servletRequest) {
        String usernameInToken = getTokenSubject(servletRequest);

        Posting foundPosting = postingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(NO_EXIST_POSTING_EXCEPTION_MSG.getMsg()));

        if (!usernameInToken.equals(foundPosting.getMemberId().getUsername())) {
            throw new IllegalJwtException(WRONG_JWT_EXCEPTION_MSG.getMsg());
        }

        if (isNotEqualPassword(foundPosting.getPassword(), requestDto.getPassword())) {
            throw new IllegalArgumentException(WRONG_PASSWORD_EXCEPTION_MSG.getMsg());
        }

        foundPosting.update(requestDto);

        return new PostingDto.Response(UPDATE_POSTING_SUCCESS_MSG, new PostingDto.Data(foundPosting));
    }

    private String getTokenSubject(HttpServletRequest servletRequest) {
        String token = jwtUtil.resolveToken(servletRequest);
        Claims claim = jwtUtil.getUserInfoFromToken(token);
        String username = claim.getSubject();
        return username;
    }

    @Transactional
    public void deleteOne(Long id, PostingDto.Request requestDto) {
        Posting foundPosting = postingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(NO_EXIST_POSTING_EXCEPTION_MSG.getMsg()));

        if (isNotEqualPassword(foundPosting.getPassword(), requestDto.getPassword())) {
            throw new IllegalArgumentException(WRONG_PASSWORD_EXCEPTION_MSG.getMsg());
        }

        postingRepository.deleteById(id);
    }

    private boolean isNotEqualPassword(String a, String b) {
        return !a.equals(b);
    }

}
