package com.hanghae.blog.member.repository;

import com.hanghae.blog.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
