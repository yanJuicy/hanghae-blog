package com.hanghae.blog.posting.controller;

import com.hanghae.blog.common.exception.custom.NotEnoughArgumentException;
import com.hanghae.blog.common.response.GenericResponseDto;
import com.hanghae.blog.posting.dto.PostingDto;
import com.hanghae.blog.posting.dto.RequestCreatePostingDto;
import com.hanghae.blog.posting.dto.ResponseCreatePostingDto;
import com.hanghae.blog.posting.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/api/postings")
@RestController
public class PostingController {

    private final PostingService postingService;

    @GetMapping
    public List<PostingDto.Response> findAllPostings() {
        return postingService.findAll();
    }

    @GetMapping("/{id}")
    public PostingDto.Response findPosting(@PathVariable Long id) {
        return postingService.findOne(id);
    }

    @PostMapping
    public GenericResponseDto<ResponseCreatePostingDto> createPosting(@RequestBody RequestCreatePostingDto requestDto, HttpServletRequest servletRequest) {
        return postingService.create(requestDto, servletRequest);
    }

    @PutMapping("/{id}")
    public PostingDto.Response updatePosting(@PathVariable Long id, @RequestBody PostingDto.Request requestDto, HttpServletRequest servletRequest) {
        if (!requestDto.isFill()) {
            throw new NotEnoughArgumentException();
        }

        return postingService.update(id, requestDto, servletRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePosting(@PathVariable Long id, @RequestBody PostingDto.Request request, HttpServletRequest servletRequest) {
        postingService.deleteOne(id, request, servletRequest);
        Map<String, Boolean> result = new HashMap<>();
        result.put("success", true);

        ResponseEntity<Map<String, Boolean>> response =
                ResponseEntity.of(Optional.of(result));

        return response;
    }

}
