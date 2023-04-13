package com.example.demo.controller;

import com.example.demo.controller.dto.RequestNotepad;
import com.example.demo.controller.dto.ResponseMember;
import com.example.demo.controller.dto.ResponseNotped;
import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
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
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<ResponseNotped>> getNotepads(){
        List<ResponseNotped> notepadList = notepadService.getNotepads();
        return ResponseEntity.ok(notepadList);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<List<ResponseNotped>> getNotepad(@PathVariable String memberId){

        Member member = memberService.getMember(memberId);

        ResponseMember resultMember = new ResponseMember(member.getMemberId(), member.getPwd());

        if (resultMember.getMemberId().equals(""))
            throw  new IllegalArgumentException("존재하지 않는 아이디입니다.");

        List<ResponseNotped> notepad = notepadService.getNotepad(new Member(resultMember.getMemberId(), resultMember.getPwd()));
        return ResponseEntity.ok(notepad);
    }

    @PostMapping("/{memberId}")
    public ResponseEntity<ResponseNotped> save(@PathVariable String memberId, @RequestBody RequestNotepad requestNotepad){

        Member member = memberService.getMember(memberId);

        ResponseMember resultMember = new ResponseMember(member.getMemberId(), member.getPwd());

        if (resultMember.getMemberId().equals(""))
            throw  new IllegalArgumentException("존재하지 않는 아이디입니다.");

        requestNotepad.setMember(new Member(resultMember.getMemberId(), resultMember.getMemberId()));
        ResponseNotped notepad = notepadService.save(requestNotepad);

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
