package com.hanghae.blog.posting.dto;

import com.hanghae.blog.comment.entity.Comment;
import com.hanghae.blog.common.exception.ExceptionMessage;
import com.hanghae.blog.common.response.ResponseMessage;
import com.hanghae.blog.posting.entity.Posting;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
    public static class Response {
        private int status;
        private String msg;
        private Data data;

        public Response(ResponseMessage responseMessage, Data data) {
            this.status = responseMessage.getStatus();
            this.msg = responseMessage.getMsg();
            this.data = data;
        }
    }

	@Getter
	public static class Data {
		private final Long id;
		private final String title;
		private final String writer;
		private final String contents;
		private final LocalDateTime lastModifiedAt;
		private List<Comment> commentList;

		public Data(Posting posting) {
			this.id = posting.getId();
			this.title = posting.getTitle();
			this.writer = posting.getWriter();
			this.contents = posting.getWriter();
			this.lastModifiedAt = posting.getLastModifiedAt();
			this.commentList = posting.getCommentList();
		}
	}

    @Getter
    public static class Exception {
        private final int status;
        private final String message;

        public Exception(ExceptionMessage exceptionMessage) {
            status = exceptionMessage.getStatus();
            message = exceptionMessage.getMsg();
        }
    }
}
