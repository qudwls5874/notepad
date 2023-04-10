package com.example.demo.controller;

import com.example.demo.controller.dto.RequestNotepad;
import com.example.demo.controller.dto.ResponseNotped;
import com.example.demo.domain.Notepad;
import com.example.demo.service.NotepadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // REST 컨트롤러 클래스
@RequestMapping("/notepads")
@RequiredArgsConstructor // 꼭 필요한 요소(final) 생성자 자동 생성
public class NotepadController {

    private final NotepadService notepadService;

    @GetMapping
    public ResponseEntity<List<ResponseNotped>> getNotepads(){
        List<ResponseNotped> notepadList = notepadService.getList();
        return ResponseEntity.ok(notepadList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseNotped> getNotepad(@PathVariable Long id){
        ResponseNotped notepad = notepadService.get(id);

        return ResponseEntity.ok(notepad);
    }

    @PostMapping
    public ResponseEntity<ResponseNotped> save(@RequestBody RequestNotepad requestMember){
        ResponseNotped notepad = notepadService.save(requestMember);
        //notepad.setStatusCode(HttpStatus.CREATED.toString());

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", notepad.getNo().toString())
                .body(notepad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseNotped> update(@PathVariable Long id, @RequestBody RequestNotepad requestNotepad){
        requestNotepad.setNo(id);
        ResponseNotped notepad = notepadService.update(requestNotepad);
        //return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.OK)
                .header("Location", notepad.getNo().toString())
                .body(notepad);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseNotped> delete(@PathVariable Long id){
        notepadService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping()
    public ResponseEntity<ResponseNotped> allDelete(){
        notepadService.allDelete();
        return ResponseEntity.status(HttpStatus.OK).build();
    }






}
