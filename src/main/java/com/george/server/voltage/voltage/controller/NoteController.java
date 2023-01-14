package com.george.server.voltage.voltage.controller;

import com.george.server.voltage.voltage.model.MessageResponse;
import com.george.server.voltage.voltage.model.Note;
import com.george.server.voltage.voltage.repository.NoteRepository;
import com.george.server.voltage.voltage.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voltage/note")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @PostMapping("/create")
    public ResponseEntity<?> saveNote(@RequestBody Note note) {
        noteRepository.save(note);
        return ResponseEntity.ok(new MessageResponse("Note successfully created!"));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateNoteById(@RequestParam(value = "id") long id,
                                            @RequestBody Note note) {
        noteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Note with id " + id + " not found")
        );

        int codeUpdate = noteRepository.updateTitleAndDescriptionById(
                note.getTitle(),
                note.getDescription(),
                id
        );

        return ResponseEntity.ok(
                new MessageResponse("Note successfully updated with code: " + codeUpdate)
        );
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteNoteById(@RequestParam(value = "id") long id) {
        noteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Note with id " + id + " not found")
        );

        noteRepository.deleteById(id);

        return ResponseEntity.ok(
                new MessageResponse("Note successfully deleted")
        );
    }

    @GetMapping("/getAll")
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

}
