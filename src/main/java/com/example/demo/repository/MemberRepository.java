package com.example.demo.repository;


import com.example.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    // 회원 아이디 중복 확인
    boolean existsByMemberId(String memberId);
/*
    // 회원
    @Query("SELECT * FROM Member WHERE memberId = :SQS")
    Member getMember(String SQS);
*/
}
