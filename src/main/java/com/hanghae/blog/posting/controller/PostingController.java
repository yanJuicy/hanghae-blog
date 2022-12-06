package com.hanghae.blog.posting.controller;

import com.hanghae.blog.posting.dto.PostingDto;
import com.hanghae.blog.common.exception.custom.NotEnoughArgumentException;
import com.hanghae.blog.posting.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/postings")
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
    public PostingDto.Response createPosting(@RequestBody final PostingDto.Request requestDto, HttpServletRequest servletRequest) {
        if (!requestDto.isFill()) {
            throw new NotEnoughArgumentException();
        }

        return postingService.create(requestDto, servletRequest);
    }

    @PutMapping("/{id}")
    public PostingDto.Response updatePosting(@PathVariable Long id, @RequestBody final PostingDto.Request request) {
        if (!request.isFill()) {
            throw new NotEnoughArgumentException();
        }

        return postingService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePosting(@PathVariable Long id, @RequestBody final PostingDto.Request request) {
        postingService.deleteOne(id, request);
        Map<String, Boolean> result = new HashMap<>();
        result.put("success", true);

        ResponseEntity<Map<String, Boolean>> response =
                ResponseEntity.of(Optional.of(result));

        return response;
    }

}
