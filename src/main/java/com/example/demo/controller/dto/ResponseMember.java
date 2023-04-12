package com.example.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor // 기본생성자를 만듭니다.
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 만듦
public class ResponseMember {

    private String memberId;
    private String pwd;

}
