package com.example.demo.controller.dto;

import com.example.demo.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 만듦
public class RequestMember {

    private String memberId;
    private String pwd;

    public Member toEntity(){
        return Member.builder()
                .memberId(memberId)
                .pwd(pwd)
                .build();
    }

}
