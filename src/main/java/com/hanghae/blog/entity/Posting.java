package com.hanghae.blog.entity;

import com.hanghae.blog.dto.PostingDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Posting extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String writer;

    @Column
    private String contents;

    @Column
    private String password;

    public void update(PostingDto.Request requestDto) {
        this.title = requestDto.getTitle();
        this.writer = requestDto.getWriter();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }
}
