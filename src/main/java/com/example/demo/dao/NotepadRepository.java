package com.example.demo.dao;

import com.example.demo.domain.Notepad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotepadRepository extends JpaRepository<Notepad, Long> {
}
