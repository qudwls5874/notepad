package com.example.demo.repository;


import com.example.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

    // 회원 아이디 중복 확인
    boolean existsByMemberId(String memberId);

}
