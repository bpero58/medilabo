package com.medilabo.assessment.client;

import com.medilabo.assessment.dto.NoteDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class NoteClient {

    private final RestTemplate restTemplate;

    @Value("${gateway.url}")
    private String gatewayUrl;

    public NoteClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<NoteDto> getNotesForPatient(Long patientId, String jwtToken) {
        String url = gatewayUrl + "/api/notes/patient/" + patientId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        NoteDto[] notes = restTemplate.exchange(url, HttpMethod.GET, entity, NoteDto[].class).getBody();
        return notes != null ? Arrays.asList(notes) : List.of();
    }
}


