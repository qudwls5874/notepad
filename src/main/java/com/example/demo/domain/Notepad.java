package com.example.demo.domain;

//@NoArgsConstructor

import lombok.*;
import javax.persistence.*;

@Entity // 테이블과 연계됨을 스프링에게 알려줍니다. DB설계도로 할거야를 알려줌
@Getter //@Setter// getter,setter를 lombok으로 쉽게 표현
@Builder // 멤버 필드들에 대해 빌더를 만들어준다.
@NoArgsConstructor // 기본생성자를 만듭니다.
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 만듦
public class Notepad {

    @Id //엔티티 객체의 식별자로 사용할 필드 >> PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 기본키를 생성
    private Long no;

    @Column(nullable = false)   // 열값 null 체크
    private String text;


}
