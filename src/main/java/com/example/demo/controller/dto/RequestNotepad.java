package com.example.demo.controller.dto;

import com.example.demo.domain.Member;
import com.example.demo.domain.Notepad;
import lombok.Getter;
import lombok.Setter;


@Setter
public class RequestNotepad {

    private Long no;
    private String text;
    private Member member;

    public Notepad toEntity(){
        return Notepad.builder()
                .no(no)
                .text(text)
                .member(member)
                .build();
    }
}
