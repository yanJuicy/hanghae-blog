package com.hanghae.blog.posting.service;

import com.hanghae.blog.common.exception.custom.IllegalJwtException;
import com.hanghae.blog.common.response.GenericResponseDto;
import com.hanghae.blog.common.response.ResponseDto;
import com.hanghae.blog.jwt.JwtService;
import com.hanghae.blog.member.entity.Member;
import com.hanghae.blog.member.service.MemberService;
import com.hanghae.blog.posting.dto.PostingDto;
import com.hanghae.blog.posting.dto.RequestCreatePostingDto;
import com.hanghae.blog.posting.dto.ResponsePostingDto;
import com.hanghae.blog.posting.entity.Posting;
import com.hanghae.blog.posting.mapper.PostingMapper;
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
import static com.hanghae.blog.common.response.ResponseMessage.DELETE_POSTING_SUCCESS_MSG;
import static com.hanghae.blog.common.response.ResponseMessage.READ_POSTING_SUCCESS_MSG;
import static com.hanghae.blog.common.response.ResponseMessage.UPDATE_POSTING_SUCCESS_MSG;

@RequiredArgsConstructor
@Service
public class PostingService {
    private final PostingRepository postingRepository;
    private final MemberService memberService;
    private final JwtService jwtService;
    private final PostingMapper postingMapper;

    public GenericResponseDto<List<ResponsePostingDto>> findAll() {
        List<Posting> postingList = postingRepository.findByOrderByCreatedAtDesc();
        List<ResponsePostingDto> responseList = postingList.stream()
                .map(e -> postingMapper.toResponse(e))
                .collect(Collectors.toList());

        return new GenericResponseDto<>(READ_POSTING_SUCCESS_MSG, responseList);
    }

    public GenericResponseDto<ResponsePostingDto> findOne(Long id) {
        Posting foundPosting = postingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(NO_EXIST_POSTING_EXCEPTION_MSG.getMsg()));

        ResponsePostingDto response = postingMapper.toResponse(foundPosting);
        return new GenericResponseDto<>(READ_POSTING_SUCCESS_MSG, response);
    }

    @Transactional
    public GenericResponseDto<ResponsePostingDto> create(RequestCreatePostingDto requestDto, HttpServletRequest servletRequest) {
        String usernameInToken = getTokenSubject(servletRequest);

        Member member = memberService.findMember(usernameInToken);

        Posting posting = postingMapper.toPosting(requestDto, member);

        Posting savedPosting = postingRepository.save(posting);

        ResponsePostingDto response = postingMapper.toResponse(savedPosting);
        return new GenericResponseDto<>(CREATE_POSTING_SUCCESS_MSG, response);
    }

    @Transactional
    public GenericResponseDto<ResponsePostingDto> update(Long id, PostingDto.Request requestDto, HttpServletRequest servletRequest) {
        String usernameInToken = getTokenSubject(servletRequest);

        Posting foundPosting = postingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(NO_EXIST_POSTING_EXCEPTION_MSG.getMsg()));

        if (!usernameInToken.equals(foundPosting.getMember().getUsername())) {
            throw new IllegalJwtException(WRONG_JWT_EXCEPTION_MSG.getMsg());
        }

        if (isNotEqualPassword(foundPosting.getPassword(), requestDto.getPassword())) {
            throw new IllegalArgumentException(WRONG_PASSWORD_EXCEPTION_MSG.getMsg());
        }

        foundPosting.update(requestDto);

        ResponsePostingDto response = postingMapper.toResponse(foundPosting);

        return new GenericResponseDto<>(UPDATE_POSTING_SUCCESS_MSG, response);
    }

    @Transactional
    public ResponseDto deleteOne(Long id, PostingDto.Request requestDto, HttpServletRequest servletRequest) {
        String usernameInToken = getTokenSubject(servletRequest);

        Posting foundPosting = postingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(NO_EXIST_POSTING_EXCEPTION_MSG.getMsg()));

        if (!usernameInToken.equals(foundPosting.getMember().getUsername())) {
            throw new IllegalJwtException(WRONG_JWT_EXCEPTION_MSG.getMsg());
        }

        if (isNotEqualPassword(foundPosting.getPassword(), requestDto.getPassword())) {
            throw new IllegalArgumentException(WRONG_PASSWORD_EXCEPTION_MSG.getMsg());
        }

        postingRepository.deleteById(id);

        return new ResponseDto(DELETE_POSTING_SUCCESS_MSG);
    }

    private String getTokenSubject(HttpServletRequest servletRequest) {
        Claims claim = jwtService.getTokenClaim(servletRequest);
        String username = claim.getSubject();
        return username;
    }

    private boolean isNotEqualPassword(String a, String b) {
        return !a.equals(b);
    }

}
