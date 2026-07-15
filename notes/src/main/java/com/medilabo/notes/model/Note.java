package com.medilabo.notes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "notes")
public class Note {
    @Id
    private String id;
    private Long patientId;
    private String physicianUsername;
    private String content;
    private LocalDateTime createdAt;

    //Getters
    public String getId() {
        return id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public String getPhysicianUsername() {
        return physicianUsername;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    //Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public void setPhysicianUsername(String physicianUsername) {
        this.physicianUsername = physicianUsername;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
