package com.hanghae.blog.posting.repository;

import com.hanghae.blog.posting.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostingRepository extends JpaRepository<Posting, Long> {
}
