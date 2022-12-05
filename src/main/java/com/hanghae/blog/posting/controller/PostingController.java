package com.hanghae.blog.posting.controller;

import com.hanghae.blog.posting.dto.PostingDto;
import com.hanghae.blog.common.custom.NotEnoughArgumentException;
import com.hanghae.blog.posting.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/postings")
@RestController
public class PostingController {

    private final PostingService postingService;

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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PostingDto.Response createPosting(@RequestBody final PostingDto.Request request) {
        if (!request.isFill()) {
            throw new NotEnoughArgumentException();
        }

        return postingService.create(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public PostingDto.Response updatePosting(@PathVariable Long id, @RequestBody final PostingDto.Request request) {
        if (!request.isFill()) {
            throw new NotEnoughArgumentException();
        }

        return postingService.update(id, request);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deletePosting(@PathVariable Long id, @RequestBody final PostingDto.Request request) {
        postingService.deleteOne(id, request);
        Map<String, Boolean> result = new HashMap<>();
        result.put("success", true);

        return result;
    }

}
