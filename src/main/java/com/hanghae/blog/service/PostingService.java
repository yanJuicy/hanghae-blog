package com.hanghae.blog.service;

import com.hanghae.blog.dto.PostingDto;
import com.hanghae.blog.entity.Posting;
import com.hanghae.blog.exception.ExceptionMessage;
import com.hanghae.blog.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        List<PostingDto.Response> responseList = postingList.stream()
                .map(e -> new PostingDto.Response(HttpStatus.OK, "조회 성공", new PostingDto.Data(e)))
                .collect(Collectors.toList());
        return responseList;
    }

    public PostingDto.Response findOne(Long id) {
        Posting foundPosting = postingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(ExceptionMessage.NO_EXIST_POSTING_EXCEPTION_MSG.getMessage()));
        return new PostingDto.Response(HttpStatus.OK, "조회 성공", new PostingDto.Data(foundPosting));
    }

    @Transactional
    public PostingDto.Response create(PostingDto.Request requestDto) {
        Posting posting = requestDto.toEntity();
        Posting savedPosting = postingRepository.save(posting);
        return new PostingDto.Response(HttpStatus.CREATED, "포스팅 생성 성공", new PostingDto.Data(savedPosting));
    }

    @Transactional
    public PostingDto.Response update(Long id, PostingDto.Request requestDto) {
        Posting foundPosting = postingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(ExceptionMessage.NO_EXIST_POSTING_EXCEPTION_MSG.getMessage()));

        if (isNotEqualPassword(foundPosting.getPassword(), requestDto.getPassword())) {
            throw new IllegalArgumentException(ExceptionMessage.WRONG_PASSWORD_EXCEPTION_MSG.getMessage());
        }

        foundPosting.update(requestDto);

        return new PostingDto.Response(HttpStatus.OK, "포스팅 생성 성공", new PostingDto.Data(foundPosting));
    }

    @Transactional
    public void deleteOne(Long id, PostingDto.Request requestDto) {
        Posting foundPosting = postingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(ExceptionMessage.NO_EXIST_POSTING_EXCEPTION_MSG.getMessage()));

        if (isNotEqualPassword(foundPosting.getPassword(), requestDto.getPassword())) {
            throw new IllegalArgumentException(ExceptionMessage.WRONG_PASSWORD_EXCEPTION_MSG.getMessage());
        }

        postingRepository.deleteById(id);
    }

    private boolean isNotEqualPassword(String a, String b) {
        return !a.equals(b);
    }

}
