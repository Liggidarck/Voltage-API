package com.george.server.voltage.voltage.controller;

import com.george.server.voltage.voltage.model.MessageResponse;
import com.george.server.voltage.voltage.model.Note;
import com.george.server.voltage.voltage.repository.NoteRepository;
import com.george.server.voltage.voltage.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @PostMapping("/create")
    public ResponseEntity<?> saveNote(@RequestBody Note note) {
        Note _note = noteRepository.save(note);
        return new ResponseEntity<>(_note, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateNoteById(@RequestParam(value = "id") long id,
                                            @RequestBody Note note) {
        noteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Note with id " + id + " not found")
        );

        int code = noteRepository.updateTitleAndDescriptionById(
                note.getTitle(),
                note.getDescription(),
                id
        );

        return new ResponseEntity<>(new MessageResponse("Note update with code " + code), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteNoteById(@RequestParam(value = "id") long id) {
        noteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Note with id " + id + " not found")
        );

        noteRepository.deleteById(id);

        return new ResponseEntity<>(new MessageResponse("Note successfully deleted"), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

}
