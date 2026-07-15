package com.medilabo.assessment.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NoteDto {
    private Long id;
    private Long patientId;
    private String physicianUsername;
    private String content;
    private LocalDateTime createdAt;
}



