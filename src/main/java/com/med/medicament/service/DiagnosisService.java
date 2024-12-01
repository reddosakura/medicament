package com.med.medicament.service;

//import com.med.medicament.model.DiagnosisModel;
//import com.med.medicament.repository.DiagnosisRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import com.med.medicament.model.DiagnosisDTO;
import com.med.medicament.model.PatientDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@Service
public class DiagnosisService {

    public List<DiagnosisDTO> getAllDiagnosis(String token) {
        RestClient restClient = RestClient.builder().build();

        return restClient.get()
                .uri("http://localhost:7979/diagnosis/all")
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public PatientDTO getDiagnosis(String token, UUID id) {
        RestClient restClient = RestClient.builder().build();

        return restClient.get()
                .uri("http://localhost:7979/diagnosis/all/" + id)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public DiagnosisDTO addDiagnosis(String token, DiagnosisDTO store) {
        RestClient restClient = RestClient.builder().build();
        return restClient.post()
                .uri("http://localhost:7979/diagnosis")
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(store)
                .retrieve()
                .body(DiagnosisDTO.class);
    }

    public PatientDTO updateDiagnosis(String token, DiagnosisDTO store, UUID id) {
        RestClient restClient = RestClient.builder().build();
        return restClient.put()
                .uri("http://localhost:7979/diagnosis/update?id=" + id)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(store)
                .retrieve()
                .body(PatientDTO.class);
    }

    public void deleteDiagnosis(String token, UUID id) {
        RestClient restClient = RestClient.builder().build();
        restClient.delete()
                .uri("http://localhost:7979/diagnosis/delete?id=" + id)
                .header("Authorization", "Bearer " + token)
                .retrieve();
    }

}
