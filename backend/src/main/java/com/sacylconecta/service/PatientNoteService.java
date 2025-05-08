package com.sacylconecta.service;

import com.sacylconecta.model.PatientNote;
import com.sacylconecta.repository.PatientNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientNoteService {

    @Autowired
    private PatientNoteRepository patientNoteRepository;

    public List<PatientNote> findByPatientId(Long patientId) {
        return patientNoteRepository.findByPatientId(patientId);
    }

    public PatientNote savePatientNote(PatientNote patientNote) {
        return patientNoteRepository.save(patientNote);
    }
}
