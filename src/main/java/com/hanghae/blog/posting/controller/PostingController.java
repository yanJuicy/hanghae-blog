package com.hanghae.blog.posting.controller;

import com.hanghae.blog.common.response.DataResponseDto;
import com.hanghae.blog.common.response.ResponseDto;
import com.hanghae.blog.posting.dto.RequestCreatePostingDto;
import com.hanghae.blog.posting.dto.RequestDeletePostingDto;
import com.hanghae.blog.posting.dto.RequestUpdatePostingDto;
import com.hanghae.blog.posting.dto.ResponsePostingDto;
import com.hanghae.blog.posting.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/postings")
@RestController
public class PostingController {

    private final PostingService postingService;

    @GetMapping
    public DataResponseDto<List<ResponsePostingDto>> findAllPostings() {
        return postingService.findAll();
    }

    @GetMapping("/{id}")
    public DataResponseDto<ResponsePostingDto> findPosting(@PathVariable Long id) {
        return postingService.findOne(id);
    }

    @PostMapping
    public DataResponseDto<ResponsePostingDto> createPosting(@RequestBody RequestCreatePostingDto requestDto,
                                                             HttpServletRequest servletRequest) {
        return postingService.create(requestDto, servletRequest);
    }

    @PutMapping("/{id}")
    public DataResponseDto<ResponsePostingDto> updatePosting(@PathVariable Long id, @RequestBody RequestUpdatePostingDto requestDto,
                                                             HttpServletRequest servletRequest) {
        return postingService.update(id, requestDto, servletRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseDto deletePosting(@PathVariable Long id, @RequestBody RequestDeletePostingDto request,
                                     HttpServletRequest servletRequest) {
        return postingService.deleteOne(id, request, servletRequest);
    }

}
