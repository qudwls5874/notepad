package com.example.demo.controller.dto;

import com.example.demo.domain.Notepad;
import lombok.Getter;
import lombok.Setter;


@Setter
public class RequestNotepad {

    private Long no;
    private String text;

    public Notepad toEntity(){
        return Notepad.builder()
                .no(no)
                .text(text)
                .build();
    }
}
