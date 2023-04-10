package com.example.demo.repository;

import com.example.demo.domain.Notepad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotepadRepository extends JpaRepository<Notepad, Long> {
}
