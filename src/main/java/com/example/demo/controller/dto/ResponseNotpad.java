package com.example.demo.controller.dto;

import com.example.demo.domain.Member;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor // 기본생성자를 만듭니다.
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 만듦
//@Setter
public class ResponseNotpad {

    private Long no;
    private String text;
    private Member member;

}
