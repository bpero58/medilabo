package com.medilabo.demographics.repository;

import com.medilabo.demographics.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// This repo pulls data from a DB its agnostic meaning that even if you switch the DB connection in dependencies it will still run
@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {
    // --- View Patient Personal Information ---
    //TODO add a cache here make sure that ONLY the data needed for each specific enpoint is called

    // Find a single patient by ID
    Optional<Patient> findById(Long id);

    // Find patients by last name (useful for coordinator identity verification)
    List<Patient> findByLastName(String lastName);

    // Find by first and last name
    List<Patient> findByFirstNameAndLastName(String firstName, String lastName);

    // Find by date of birth (additional identity verification)
    List<Patient> findByDateOfBirth(LocalDate dateOfBirth);

    // Find by last name and date of birth (strong identity check)
    List<Patient> findByLastNameAndDateOfBirth(String lastName, LocalDate dateOfBirth);
}
