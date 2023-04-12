package com.example.demo.service;

import com.example.demo.controller.dto.RequestMember;
import com.example.demo.controller.dto.ResponseMember;
import com.example.demo.controller.dto.ResponseNotped;
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

    public List<ResponseMember> getMembers(){
        List<Member> members =  memberRepository.findAll();

        return members.stream()
                .map(member -> new ResponseMember(member.getMemberId(), member.getPwd()))
                .collect(Collectors.toList());
    }

    public ResponseMember getMember(String id){
        Member resultMember = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return new ResponseMember(resultMember.getMemberId(), resultMember.getPwd());
    }

    public ResponseMember save(RequestMember requestUser){
        if (memberRepository.existsByMemberId(requestUser.getMemberId()))
            throw  new IllegalArgumentException("이미 존재하는 아이디입니다.");

        Member resultMember = memberRepository.save(requestUser.toEntity());
        return new ResponseMember(resultMember.getMemberId(), resultMember.getPwd());
    }


}
