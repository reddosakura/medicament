package com.med.medicament.service;

//import com.med.medicament.model.CardModel;
//import com.med.medicament.repository.CardRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import com.med.medicament.model.PatientCreateDTO;
import com.med.medicament.model.PatientDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@Service
public class MedCardService {

    public List<PatientDTO> getAllCards(String token) {
        RestClient restClient = RestClient.builder().build();

        return restClient.get()
                .uri("http://localhost:7979/patients/all")
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public PatientDTO getCard(String token, UUID id) {
        RestClient restClient = RestClient.builder().build();

        return restClient.get()
                .uri("http://localhost:7979/patients/all/" + id)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }


    public PatientDTO getCardByUserID(String token, UUID id) {
        RestClient restClient = RestClient.builder().build();

        return restClient.get()
                .uri("http://localhost:7979/patients/user/" + id)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public PatientCreateDTO addCard(String token, PatientCreateDTO store) {
        RestClient restClient = RestClient.builder().build();
        return restClient.post()
                .uri("http://localhost:7979/patients")
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(store)
                .retrieve()
                .body(PatientCreateDTO.class);
    }

    public PatientDTO updateCard(String token, PatientDTO store, UUID id) {
        RestClient restClient = RestClient.builder().build();
        return restClient.put()
                .uri("http://localhost:7979/patients/update?id=" + id)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(store)
                .retrieve()
                .body(PatientDTO.class);
    }

    public void deleteCard(String token, UUID id) {
        RestClient restClient = RestClient.builder().build();
        restClient.delete()
                .uri("http://localhost:7979/stores/delete?id=" + id)
                .header("Authorization", "Bearer " + token)
                .retrieve();
    }

    public PatientDTO findCardsByLastname(String token, String lastname) {
        RestClient restClient = RestClient.builder().build();

        return restClient.get()
                .uri("http://localhost:7979/patients/search?keyword=" + lastname)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public List<PatientDTO> sortCardsASC(String token) {
        RestClient restClient = RestClient.builder().build();

        return restClient.get()
                .uri("http://localhost:7979/patients/sort/asc")
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public List<PatientDTO> sortCardsDESC(String token) {
        RestClient restClient = RestClient.builder().build();

        return restClient.get()
                .uri("http://localhost:7979/patients/sort/desc")
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

}
