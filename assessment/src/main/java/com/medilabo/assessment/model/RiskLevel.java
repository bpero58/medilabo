package com.medilabo.assessment.model;

public enum RiskLevel {
    NONE("None"),
    BORDERLINE("Borderline"),
    IN_DANGER("InDanger"),
    EARLY_ONSET("EarlyOnset");

    private final String label;

    RiskLevel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}


