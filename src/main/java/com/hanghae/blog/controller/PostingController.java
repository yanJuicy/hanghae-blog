package com.hanghae.blog.controller;

import com.hanghae.blog.dto.PostingDto;
import com.hanghae.blog.entity.Posting;
import com.hanghae.blog.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/postings")
@RestController
public class PostingController {

    private final PostingService postingService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PostingDto.Response createPosting(@RequestBody final PostingDto.Request request) {
        Posting savedPosting = postingService.create(request);
        return new PostingDto.Response(savedPosting);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PostingDto.Response> findAllPostings() {
        return postingService.findAll();
    }


}
