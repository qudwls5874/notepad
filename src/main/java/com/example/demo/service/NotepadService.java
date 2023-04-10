package com.example.demo.service;

import com.example.demo.dao.NotepadRepository;
import com.example.demo.domain.Notepad;
import com.example.demo.dto.RequestNotepad;
import com.example.demo.dto.ResponseNotepad;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // 서비스 클래스
@RequiredArgsConstructor    // 꼭 필요한 요소(final) 생성자 자동 생성
public class NotepadService {

    private final NotepadRepository notepadRepository;
    private final ModelMapper modelMapper;  // Object에 있는 필드값들을 자동으로 원하는 Object로 Mapping 시킴

    public ResponseNotepad save(RequestNotepad requestNotepad){
        Notepad notepad = notepadRepository.save(requestNotepad.toEntity());
        return modelMapper.map(notepad, ResponseNotepad.class);
    }

    public ResponseNotepad update(RequestNotepad requestNotepad){
        Notepad notepad = notepadRepository.save(requestNotepad.toEntity());
        return  modelMapper.map(notepad, ResponseNotepad.class);
    }

    public List<ResponseNotepad> getList() {
        List<Notepad> noteList = notepadRepository.findAll();

        return noteList.stream()
                .map(member -> modelMapper.map(member, ResponseNotepad.class))
                .collect(Collectors.toList());
    }

    public ResponseNotepad get(Long id) {
        Notepad notepad = notepadRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return modelMapper.map(notepad,ResponseNotepad.class);
    }

    public void delete(Long id) {
        if(notepadRepository.existsById(id)){
            notepadRepository.deleteById(id);
        }else{
            throw new IllegalArgumentException();
        }
    }

    public void allDelete(){
        notepadRepository.deleteAll();
    }

}
