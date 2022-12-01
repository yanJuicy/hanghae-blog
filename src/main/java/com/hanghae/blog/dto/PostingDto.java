package com.hanghae.blog.dto;

import com.hanghae.blog.entity.Posting;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class PostingDto {

    @Getter
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

        public boolean isFill() {
            if (title == null || writer == null || contents == null || password == null) {
                return false;
            }

            return true;
        }
    }

    @Getter
    public static class Data {
        private final Long id;
        private final String title;
        private final String writer;
        private final String contents;
        private final LocalDateTime lastModifiedAt;

        public Data(Posting posting) {
            this.id = posting.getId();
            this.title = posting.getTitle();
            this.writer = posting.getWriter();
            this.contents = posting.getWriter();
            this.lastModifiedAt = posting.getLastModifiedAt();
        }
    }


    @Getter
    @RequiredArgsConstructor
    public static class Response {
        private final HttpStatus httpStatus;
        private final String msg;
        private final Data data;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Exception {
        private final String message;
    }
}
