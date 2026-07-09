package com.medilabo.notes.dto;

public class NoteRequest {
    private Long patientId;
    private String physicianUsername;
    private String content;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPhysicianUsername() {
        return physicianUsername;
    }

    public void setPhysicianUsername(String physicianUsername) {
        this.physicianUsername = physicianUsername;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
