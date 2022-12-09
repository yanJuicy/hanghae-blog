package com.hanghae.blog.posting.entity;

import com.hanghae.blog.comment.entity.Comment;
import com.hanghae.blog.common.entity.Timestamped;
import com.hanghae.blog.member.entity.Member;
import com.hanghae.blog.posting.dto.PostingDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.sound.midi.MetaMessage;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Posting extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    private Member member;

    @OneToMany(mappedBy = "posting")
    private List<Comment> commentList = new ArrayList<>();

	public Posting(String title, String writer, String contents, String password, Member member) {
		this.title = title;
		this.writer = writer;
		this.contents = contents;
		this.password = password;
		this.member = member;
	}

	public void update(PostingDto.Request requestDto) {
        this.title = requestDto.getTitle();
        this.writer = requestDto.getWriter();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }

    public void addComment(Comment comment) {
        comment.setPosting(this);
    }
}
