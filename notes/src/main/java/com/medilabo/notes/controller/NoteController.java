package com.medilabo.notes.controller;

import com.medilabo.notes.model.Note;
import com.medilabo.notes.repository.NoteRepo;
import com.medilabo.notes.dto.NoteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired //This is just a reference that spring fills in via @Autowired
    private NoteRepo noteRepo;

    //ResponseEntity is a wrapper class for the entire HTTP response
    //It includes not only the body, but also the status code and headers giving explicit control over what is sent to the client
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Note>> getNotesForPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(noteRepo.findByPatientIdOrderByCreatedAtDesc(patientId));
    }

    //Springdata gives me .save by default its used here for post mapping and saves me from writing actual queries
    @PostMapping
    public ResponseEntity<Note> addNote(@RequestBody NoteRequest request) {
        Note note = new Note();
        note.setPatientId(request.getPatientId());
        note.setPhysicianUsername(request.getPhysicianUsername());
        note.setContent(request.getContent());
        note.setCreatedAt(LocalDateTime.now()); //Use the time in the backend (server-side) instead of relying on the client to supply it
        return ResponseEntity.ok(noteRepo.save(note));
    }
}
