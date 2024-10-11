package com.med.medicament.service;

//import com.med.medicament.model.CabinetsModel;
//import com.med.medicament.repository.CabinetsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import com.med.medicament.model.CabinetDTO;
import com.med.medicament.model.PatientCreateDTO;
import com.med.medicament.model.PatientDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@Service
public class CabinetService {

    public List<CabinetDTO> getAllCabinetss(String token) {
        RestClient restClient = RestClient.builder().build();

        return restClient.get()
                .uri("http://localhost:7979/cabinets/all")
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public PatientDTO getCabinets(String token, UUID id) {
        RestClient restClient = RestClient.builder().build();

        return restClient.get()
                .uri("http://localhost:7979/cabinets/all/" + id)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public CabinetDTO addCabinets(String token, CabinetDTO store) {
        RestClient restClient = RestClient.builder().build();
        return restClient.post()
                .uri("http://localhost:7979/cabinets")
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(store)
                .retrieve()
                .body(CabinetDTO.class);
    }

//    public PatientDTO updateCabinets(String token, PatientDTO store, UUID id) {
//        RestClient restClient = RestClient.builder().build();
//        return restClient.put()
//                .uri("http://localhost:7979/cabinets/update?id=" + id)
//                .header("Authorization", "Bearer " + token)
//                .header("Content-Type", "application/json")
//                .body(store)
//                .retrieve()
//                .body(PatientDTO.class);
//    }

    public void deleteCabinets(String token, UUID id) {
        RestClient restClient = RestClient.builder().build();
        restClient.delete()
                .uri("http://localhost:7979/cabinets/delete?id=" + id)
                .header("Authorization", "Bearer " + token)
                .retrieve();
    }

}