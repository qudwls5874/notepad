package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseNotepad {

    private Long no;
    private String text;

    public String resourceLocation(){return "/Notepad/"+no;}

/*
    private String statusCode;
    public String returnCode(){return "/status code/" + statusCode;}
*/
}
