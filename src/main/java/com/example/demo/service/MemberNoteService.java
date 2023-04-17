package com.example.demo.service;

import com.example.demo.controller.dto.RequestNotepad;
import com.example.demo.domain.Member;
import com.example.demo.domain.Notepad;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.NotepadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberNoteService {

    private final MemberRepository memberRepository;
    private final NotepadRepository notepadRepository;

    public List<Notepad> select(String id){
        Member member = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return notepadRepository.selectNotpad(member);
    }

    public Notepad save(String memberId, RequestNotepad requestNotepad){
        requestNotepad.setNo(null);
        requestNotepad.setMember(memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new));
        return notepadRepository.save(requestNotepad.toEntity());
    }

    public Notepad update(String memberId, RequestNotepad requestNotepad){

        if (!notepadRepository.findById(requestNotepad.toEntity().getNo()).get().getMember().getMemberId().equals(memberId))
            throw new IllegalArgumentException("작성한 아이디가 아닙니다.");

        requestNotepad.setMember(memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new));
        return notepadRepository.save(requestNotepad.toEntity());
    }

    public void delete(String memberId, Long no){
        if (memberRepository.existsByMemberId(memberId) && notepadRepository.existsById(no)){
            notepadRepository.deleteById(no);
        }else {
            throw new IllegalArgumentException();
        }
    }

    public void allDelete(String memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
        notepadRepository.deleteNotpad(member);
    }

}
