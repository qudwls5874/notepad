package com.example.demo;

import com.example.demo.controller.dto.RequestMember;
import com.example.demo.controller.dto.ResponseMember;
import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class Runner implements ApplicationRunner {

    private final MemberService memberService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Member member = Member.builder().memberId("test").pwd("1234").build();
        memberService.save(new RequestMember(member.getMemberId(), member.getPwd()));

        member = Member.builder().memberId("admin").pwd("1234").build();
        memberService.save(new RequestMember(member.getMemberId(), member.getPwd()));
    }
}
