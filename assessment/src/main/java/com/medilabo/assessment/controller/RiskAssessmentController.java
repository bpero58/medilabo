package com.medilabo.assessment.controller;

import com.medilabo.assessment.client.NoteClient;
import com.medilabo.assessment.client.PatientClient;
import com.medilabo.assessment.dto.NoteDto;
import com.medilabo.assessment.dto.PatientDto;
import com.medilabo.assessment.model.RiskLevel;
import com.medilabo.assessment.service.AgeCalculatorService;
import com.medilabo.assessment.service.RiskCalculatorService;
import com.medilabo.assessment.service.TriggerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/assess")
public class RiskAssessmentController {

    private final PatientClient patientClient;
    private final NoteClient noteClient;
    private final AgeCalculatorService ageCalculatorService;
    private final TriggerService triggerService;
    private final RiskCalculatorService riskCalculatorService;

    public RiskAssessmentController(PatientClient patientClient, NoteClient noteClient,
                                    AgeCalculatorService ageCalculatorService, TriggerService triggerService,
                                    RiskCalculatorService riskCalculatorService) {
        this.patientClient = patientClient;
        this.noteClient = noteClient;
        this.ageCalculatorService = ageCalculatorService;
        this.triggerService = triggerService;
        this.riskCalculatorService = riskCalculatorService;
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<String> assessRisk(@PathVariable Long patientId,
                                             @RequestHeader("Authorization") String authHeader) {

        String jwtToken = authHeader.replace("Bearer ", "");

        PatientDto patient = patientClient.getPatientById(patientId, jwtToken);
        List<NoteDto> notes = noteClient.getNotesForPatient(patientId, jwtToken);

        List<String> noteContents = notes.stream()
                .map(NoteDto::getContent)
                .collect(Collectors.toList());

        int age = ageCalculatorService.calculateAge(patient.getDateOfBirth());
        int triggerCount = triggerService.countTriggerTerms(noteContents);

        RiskLevel risk = riskCalculatorService.calculateRisk(age, patient.getGender(), triggerCount);

        return ResponseEntity.ok(risk.toString());
    }
}

