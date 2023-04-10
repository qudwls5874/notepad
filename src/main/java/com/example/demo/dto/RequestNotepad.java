package com.example.demo.dto;

import com.example.demo.domain.Notepad;
import lombok.Getter;
import lombok.Setter;

@Getter
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
