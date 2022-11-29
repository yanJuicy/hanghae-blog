package com.hanghae.blog.service;

import com.hanghae.blog.dto.PostingDto;
import com.hanghae.blog.entity.Posting;
import com.hanghae.blog.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostingService {
    private static final String NO_POSTING_EXCEPTION_MSG = "해당 포스팅이 존재하지 않습니다.";
    private static final String WRONG_PASSWORD_EXCEPTION_MSG = "잘못된 비밀번호 값 입니다.";

    private final PostingRepository postingRepository;

    public List<PostingDto.Response> findAll() {
        List<Posting> postingList = postingRepository.findAll();
        List<PostingDto.Response> responseList =
                postingList.stream().map(e -> new PostingDto.Response(e)).collect(Collectors.toList());
        return responseList;
    }

    public PostingDto.Response findOne(Long id) {
        Posting foundPosting = postingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(NO_POSTING_EXCEPTION_MSG));
        return new PostingDto.Response(foundPosting);
    }

    @Transactional
    public PostingDto.Response create(PostingDto.Request requestDto) {
        Posting posting = requestDto.toEntity();
        Posting savedPosting = postingRepository.save(posting);
        return new PostingDto.Response(savedPosting);
    }

    @Transactional
    public PostingDto.Response update(Long id, PostingDto.Request requestDto) {
        Posting foundPosting = postingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(NO_POSTING_EXCEPTION_MSG));

        if (isNotEqualPassword(foundPosting.getPassword(), requestDto.getPassword())) {
            throw new IllegalArgumentException(WRONG_PASSWORD_EXCEPTION_MSG);
        }

        foundPosting.update(requestDto);
        postingRepository.save(foundPosting);

        return new PostingDto.Response(foundPosting);
    }

    private boolean isNotEqualPassword(String a, String b) {
        return !a.equals(b);
    }

}
