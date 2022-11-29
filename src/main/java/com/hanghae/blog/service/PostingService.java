package com.hanghae.blog.service;

import com.hanghae.blog.dto.PostingDto;
import com.hanghae.blog.entity.Posting;
import com.hanghae.blog.exception.ExceptionMessage;
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
    private final PostingRepository postingRepository;

    public List<PostingDto.Response> findAll() {
        List<Posting> postingList = postingRepository.findAll();
        List<PostingDto.Response> responseList =
                postingList.stream().map(e -> new PostingDto.Response(e)).collect(Collectors.toList());
        return responseList;
    }

    public PostingDto.Response findOne(Long id) {
        Posting foundPosting = postingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(ExceptionMessage.NO_EXIST_POSTING_EXCEPTION_MSG.getMessage()));
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
                .orElseThrow(() -> new NoSuchElementException(ExceptionMessage.NO_EXIST_POSTING_EXCEPTION_MSG.getMessage()));

        if (isNotEqualPassword(foundPosting.getPassword(), requestDto.getPassword())) {
            throw new IllegalArgumentException(ExceptionMessage.WRONG_PASSWORD_EXCEPTION_MSG.getMessage());
        }

        foundPosting.update(requestDto);
        postingRepository.save(foundPosting);

        return new PostingDto.Response(foundPosting);
    }

    private boolean isNotEqualPassword(String a, String b) {
        return !a.equals(b);
    }

}
