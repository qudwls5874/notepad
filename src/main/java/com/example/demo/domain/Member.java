package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity // 테이블과 연계됨을 스프링에게 알려줍니다. DB설계도로 할거야를 알려줌
@Getter //@Setter// getter,setter를 lombok으로 쉽게 표현
@Builder // 멤버 필드들에 대해 빌더를 만들어준다.
@NoArgsConstructor // 기본생성자를 만듭니다.
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 만듦
public class Member {

    @Id
    private String memberId;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pwd;


}
