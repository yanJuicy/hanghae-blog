package com.hanghae.blog.dto;

import com.hanghae.blog.entity.Posting;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

public class PostingDto {

    @RequiredArgsConstructor
    public static class Request {
        private final String title;
        private final String writer;
        private final String contents;
        private final String password;

        public Posting toEntity() {
            return Posting.builder()
                    .title(title)
                    .writer(writer)
                    .contents(contents)
                    .password(password)
                    .build();
        }
    }


    @Getter
    public static class Response {
        private final Long id;
        private final String title;
        private final String writer;
        private final String contents;
        private final LocalDateTime lastModifiedAt;

        public Response(Posting posting) {
            this.id = posting.getId();
            this.title = posting.getTitle();
            this.writer = posting.getWriter();
            this.contents = posting.getWriter();
            this.lastModifiedAt = posting.getLastModifiedAt();
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class Exception {
        private final String message;
    }
}
