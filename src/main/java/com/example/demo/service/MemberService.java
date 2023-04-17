package com.example.demo.service;

import com.example.demo.controller.dto.RequestMember;
import com.example.demo.controller.dto.ResponseMember;
import com.example.demo.controller.dto.ResponseNotpad;
import com.example.demo.domain.Member;
import com.example.demo.domain.Notepad;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> getMembers(){
        return memberRepository.findAll();
    }

    public Member getMember(String id){
        return memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Member save(RequestMember requestUser){
        return memberRepository.save(requestUser.toEntity());
    }

    public boolean checkMemberId(String memberId){
        return memberRepository.existsByMemberId(memberId);
    }

}
