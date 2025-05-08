package com.sacylconecta.controller;

import com.sacylconecta.model.PatientNote;
import com.sacylconecta.service.PatientNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/patient-notes")
public class PatientNoteController {

    @Autowired
    private PatientNoteService patientNoteService;

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<PatientNote>> getNotesByPatient(@PathVariable Long patientId) {
        List<PatientNote> notes = patientNoteService.findByPatientId(patientId);
        return ResponseEntity.ok(notes);
    }

    @PostMapping
    public ResponseEntity<PatientNote> addPatientNote(@RequestBody PatientNote patientNote) {
        PatientNote savedNote = patientNoteService.savePatientNote(patientNote);
        return ResponseEntity.ok(savedNote);
    }
}
