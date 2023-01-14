package com.george.server.voltage.voltage.repository;

import com.george.server.voltage.voltage.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}