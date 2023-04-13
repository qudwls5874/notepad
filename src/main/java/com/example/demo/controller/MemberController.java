package com.example.demo.controller;

import com.example.demo.controller.dto.RequestMember;
import com.example.demo.controller.dto.ResponseMember;
import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<ResponseMember>> getMembers(){

        List<ResponseMember> memberList =
                memberService.getMembers().stream()
                .map(member -> new ResponseMember(member.getMemberId(), member.getPwd()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(memberList);
    }

    @PostMapping()
    public ResponseEntity<ResponseMember> save(@RequestBody RequestMember requestMember){

        if (memberService.checkMemberId(requestMember.getMemberId()))
            throw  new IllegalArgumentException("이미 존재하는 아이디입니다.");

        Member member = memberService.save(requestMember);

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", member.getMemberId())
                .body(new ResponseMember(member.getMemberId(), member.getPwd()));
    }



}
