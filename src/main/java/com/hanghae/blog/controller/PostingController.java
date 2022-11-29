package com.hanghae.blog.controller;

import com.hanghae.blog.dto.PostingDto;
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
        return postingService.create(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PostingDto.Response> findAllPostings() {
        return postingService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public PostingDto.Response findPosting(@PathVariable Long id) {
        return postingService.findOne(id);
    }


}
