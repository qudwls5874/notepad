package com.example.demo.controller;

import com.example.demo.controller.dto.RequestMember;
import com.example.demo.controller.dto.ResponseMember;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<ResponseMember>> getMembers(){
        return ResponseEntity.ok(memberService.getMembers());
    }

    @PostMapping()
    public ResponseEntity<ResponseMember> save(@RequestBody RequestMember requestMember){
        ResponseMember member = memberService.save(requestMember);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", member.getMemberId())
                .body(member);
    }



}
