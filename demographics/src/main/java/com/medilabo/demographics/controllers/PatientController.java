package com.medilabo.demographics.controllers;

import com.medilabo.demographics.domain.Patient;
import com.medilabo.demographics.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins="*")
public class PatientController {
    private final PatientService patientService;

    // Constructor injection
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // --- View Patient Personal Information ---

    // Get all patients
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    // Get patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Get patients by last name
    @GetMapping("/search")
    public ResponseEntity<List<Patient>> getPatientsByLastName(@RequestParam String lastName) {
        List<Patient> patients = patientService.getPatientsByLastName(lastName);
        return ResponseEntity.ok(patients);
    }

    // --- Add Patient Personal Information ---

    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientService.addPatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
    }

    // --- Update Patient Personal Information ---

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        return patientService.updatePatient(id, updatedPatient).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
