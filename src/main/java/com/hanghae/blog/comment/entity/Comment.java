package com.hanghae.blog.comment.entity;

import com.hanghae.blog.comment.dto.UpdateCommentRequestDto;
import com.hanghae.blog.common.entity.Timestamped;
import com.hanghae.blog.member.entity.Member;
import com.hanghae.blog.posting.entity.Posting;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Getter
@Entity
public class Comment extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "posting_id", nullable = false)
    private Posting posting;

    protected Comment() {}

    public Comment(String content, Member member, Posting posting) {
        this.content = content;
        this.member = member;
        this.posting = posting;
    }

    public void setPosting(Posting posting) {
        if (Objects.nonNull(this.posting)) {
            this.posting.getCommentList().remove(this);
        }

        this.posting = posting;
		posting.getCommentList().add(this);
    }

    public void update(UpdateCommentRequestDto requestDto) {
        this.content = requestDto.getContent();
    }
}
