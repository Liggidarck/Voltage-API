package com.george.server.voltage.voltage.repository;

import com.george.server.voltage.voltage.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    @Transactional
    @Modifying
    @Query("update Note n set n.title = ?1, n.description = ?2 where n.id = ?3")
    int updateTitleAndDescriptionById(String title, String description, Long id);
}