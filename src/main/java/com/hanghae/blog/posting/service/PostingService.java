package com.hanghae.blog.posting.service;

import com.hanghae.blog.common.exception.custom.IllegalJwtException;
import com.hanghae.blog.common.response.GenericResponseDto;
import com.hanghae.blog.jwt.JwtService;
import com.hanghae.blog.member.entity.Member;
import com.hanghae.blog.member.service.MemberService;
import com.hanghae.blog.posting.dto.PostingDto;
import com.hanghae.blog.posting.dto.RequestCreatePostingDto;
import com.hanghae.blog.posting.dto.ResponseCreatePostingDto;
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
import static com.hanghae.blog.common.response.ResponseMessage.READ_POSTING_SUCCESS_MSG;
import static com.hanghae.blog.common.response.ResponseMessage.UPDATE_POSTING_SUCCESS_MSG;

@RequiredArgsConstructor
@Service
public class PostingService {
    private final PostingRepository postingRepository;
    private final MemberService memberService;
    private final JwtService jwtService;
	private final PostingMapper postingMapper;

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
    public GenericResponseDto<ResponseCreatePostingDto> create(RequestCreatePostingDto requestDto, HttpServletRequest servletRequest) {
        String usernameInToken = getTokenSubject(servletRequest);

        Member member = memberService.findMember(usernameInToken);

		Posting posting = postingMapper.toPosting(requestDto, member);
/*        Posting posting = Posting.builder()
                .title(requestDto.getTitle())
                .writer(requestDto.getWriter())
                .contents(requestDto.getContents())
                .password(requestDto.getPassword())
                .member(member)
                .build();*/
        Posting savedPosting = postingRepository.save(posting);
        return postingMapper.toResponse(savedPosting);
    }

    @Transactional
    public PostingDto.Response update(Long id, PostingDto.Request requestDto, HttpServletRequest servletRequest) {
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

        return new PostingDto.Response(UPDATE_POSTING_SUCCESS_MSG, new PostingDto.Data(foundPosting));
    }

    @Transactional
    public void deleteOne(Long id, PostingDto.Request requestDto, HttpServletRequest servletRequest) {
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
