package com.example.demo.controller;

import com.example.demo.controller.dto.RequestNotepad;
import com.example.demo.controller.dto.ResponseMember;
import com.example.demo.controller.dto.ResponseNotpad;
import com.example.demo.domain.Member;
import com.example.demo.domain.Notepad;
import com.example.demo.service.MemberNoteService;
import com.example.demo.service.MemberService;
import com.example.demo.service.NotepadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController // REST 컨트롤러 클래스
@RequestMapping("/notepads")
@RequiredArgsConstructor // 꼭 필요한 요소(final) 생성자 자동 생성
public class NotepadController {

    private final NotepadService notepadService;
    private final MemberNoteService memberNoteService;

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<ResponseNotpad>> getNotepads(){
        List<ResponseNotpad> notepadList = notepadService.getNotepads();
        return ResponseEntity.ok(notepadList);
    }

    // 부분 조회
    @GetMapping("/{memberId}")
    public ResponseEntity<List<ResponseNotpad>> getMemberNote(@PathVariable String memberId){

        List<Notepad> result = memberNoteService.select(memberId);

        return ResponseEntity.ok(
                result.stream()
                        .map(notepad -> new ResponseNotpad(notepad.getNo(), notepad.getText(), notepad.getMember()))
                        .collect(Collectors.toList())
        );
    }

    // 저장
    @PostMapping("/{memberId}")
    public ResponseEntity<ResponseNotpad> save(@PathVariable String memberId, @RequestBody RequestNotepad requestNotepad){
        Notepad result = memberNoteService.save(memberId, requestNotepad);
        ResponseNotpad notepad = new ResponseNotpad(result.getNo(), result.getText(), result.getMember());
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", notepad.getNo().toString())
                .body(notepad);
    }

    // 수정
    @PutMapping("/{memberId}")
    public ResponseEntity<ResponseNotpad> update(@PathVariable String memberId, @RequestBody RequestNotepad requestNotepad){
        Notepad result = memberNoteService.update(memberId, requestNotepad);
        ResponseNotpad responseNotpad = new ResponseNotpad(result.getNo(), result.getText(), result.getMember());
        return ResponseEntity.status(HttpStatus.OK)
                .header("Location", responseNotpad.getNo().toString())
                .body(responseNotpad);
    }

    // 부분삭제
    @DeleteMapping("/{memberId}/{no}")
    public ResponseEntity<ResponseNotpad> delete(@PathVariable String memberId, @PathVariable Long no){
        memberNoteService.delete(memberId, no);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    // 작성자 기준 전체삭제
    @DeleteMapping("/{memberId}")
    public ResponseEntity<ResponseNotpad> allDelete(@PathVariable String memberId){
        memberNoteService.allDelete(memberId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }











}
