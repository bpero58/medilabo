package com.medilabo.demographics.service;

import com.medilabo.demographics.domain.Patient;
import com.medilabo.demographics.repository.PatientRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepo patientRepo;

    // Constructor injection (recommended over @Autowired)
    public PatientService(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    // --- View Patient Personal Information ---

    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepo.findById(id);
    }

    public List<Patient> getPatientsByLastName(String lastName) {
        return patientRepo.findByLastName(lastName);
    }

    // --- Add Patient Personal Information ---

    public Patient addPatient(Patient patient) {
        return patientRepo.save(patient);
    }

    // --- Update Patient Personal Information ---

    public Optional<Patient> updatePatient(Long id, Patient updatedPatient) {
        return patientRepo.findById(id).map(existingPatient -> {
            existingPatient.setLastName(updatedPatient.getLastName());
            existingPatient.setFirstName(updatedPatient.getFirstName());
            existingPatient.setDateOfBirth(updatedPatient.getDateOfBirth());
            existingPatient.setGender(updatedPatient.getGender());
            existingPatient.setAddress(updatedPatient.getAddress());
            existingPatient.setPhone(updatedPatient.getPhone());
            return patientRepo.save(existingPatient);
        });
    }
}
