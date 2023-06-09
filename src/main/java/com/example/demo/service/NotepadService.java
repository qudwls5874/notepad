package com.example.demo.service;

import com.example.demo.controller.dto.RequestNotepad;
import com.example.demo.controller.dto.ResponseNotpad;
import com.example.demo.domain.Member;
import com.example.demo.domain.Notepad;
import com.example.demo.repository.NotepadRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service // 서비스 클래스
@RequiredArgsConstructor    // 꼭 필요한 요소(final) 생성자 자동 생성
public class NotepadService {

    private final NotepadRepository notepadRepository;


    public List<ResponseNotpad> getNotepads() {

        List<Notepad> noteList = notepadRepository.findAll();
        return noteList.stream()
                .map(notepad -> new ResponseNotpad(notepad.getNo(), notepad.getText() , notepad.getMember()))
                .collect(Collectors.toList());
    }

}
