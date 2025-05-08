package com.sacylconecta.controller;

import com.sacylconecta.model.Appointment;
import com.sacylconecta.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        List<Appointment> appointments = appointmentService.findByDoctorId(doctorId);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatient(@PathVariable Long patientId) {
        List<Appointment> appointments = appointmentService.findByPatientId(patientId);
        return ResponseEntity.ok(appointments);
    }

    @PostMapping
    public ResponseEntity<Appointment> createOrUpdateAppointment(@RequestBody Appointment appointment) {
        Appointment savedAppointment = appointmentService.saveAppointment(appointment);
        return ResponseEntity.ok(savedAppointment);
    }

    @PostMapping("/move-up/{appointmentId}")
    public ResponseEntity<?> moveUpAppointment(@PathVariable Long appointmentId) {
        Optional<Appointment> appointmentOpt = appointmentService.findById(appointmentId);
        if (appointmentOpt.isPresent()) {
            Appointment appointment = appointmentOpt.get();
            // Move up the appointment time by 1 hour (example logic)
            appointment.setAppointmentTime(appointment.getAppointmentTime().minusHours(1));
            appointmentService.saveAppointment(appointment);
            return ResponseEntity.ok(appointment);
        }
        return ResponseEntity.notFound().build();
    }
}
