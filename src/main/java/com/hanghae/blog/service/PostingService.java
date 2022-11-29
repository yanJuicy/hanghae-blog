package com.hanghae.blog.service;

import com.hanghae.blog.dto.PostingDto;
import com.hanghae.blog.entity.Posting;
import com.hanghae.blog.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostingService {

    private final PostingRepository postingRepository;

    @Transactional
    public Posting create(PostingDto.Request requestDto) {
        Posting posting = requestDto.toEntity();
        Posting savedPosting = postingRepository.save(posting);
        return savedPosting;
    }

}
