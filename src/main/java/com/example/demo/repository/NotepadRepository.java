package com.example.demo.repository;

import com.example.demo.domain.Member;
import com.example.demo.domain.Notepad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NotepadRepository extends JpaRepository<Notepad, Long> {

    @Query(value = "SELECT note FROM Notepad note WHERE note.member = :member ")
    List<Notepad> selectNotpad(@Param(value = "member") Member member);

    @Modifying
    @Transactional
    @Query("DELETE FROM Notepad note WHERE note.member = :member ")
    void deleteNotpad(@Param(value = "member") Member member);



/*
    // JPQL 일반 파라미터 쿼리, @Param 사용 O
    @Query(value = "select sn from Snack sn where sn.id > :id")
    public List<Snack> selectJPQLById2(@Param(value = "id") int id);
*/

}
