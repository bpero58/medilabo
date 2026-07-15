package com.medilabo.assessment.service;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TriggerService {

    private static final List<String> TRIGGER_TERMS = List.of("Hemoglobin A1C", "Microalbumin", "Height", "Weight", "Smoking", "Abnormal", "Cholesterol", "Dizziness", "Relapse", "Reaction", "Antibody");

    public int countTriggerTerms(List<String> notes) {
        int count = 0;
        for (String term : TRIGGER_TERMS) {
            for (String note : notes) {
                if (note.contains(term)) {
                    count++;
                    break; // only count each trigger term once total, not once per note
                }
            }
        }
        return count;
    }
}

