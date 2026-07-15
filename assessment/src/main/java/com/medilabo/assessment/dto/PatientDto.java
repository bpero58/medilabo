package com.medilabo.assessment.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PatientDto {
    private Long id;
    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private String phone;
}



