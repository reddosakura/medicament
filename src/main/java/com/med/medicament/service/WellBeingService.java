package com.med.medicament.service;

//import com.med.medicament.model.WellBeingModel;
//import com.med.medicament.repository.WellBeingRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import com.med.medicament.model.WellBeingDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@Service
public class WellBeingService {

    public List<WellBeingDTO> getAllWellBeings(String token) {
        RestClient restClient = RestClient.builder().build();

        return restClient.get()
                .uri("http://localhost:7979/wellbeings/all")
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public List<WellBeingDTO> getWellBeingsById(String token, String id) {
        RestClient restClient = RestClient.builder().build();

        return restClient.get()
                .uri("http://localhost:7979/wellbeings/all/" + id)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }


    public WellBeingDTO addWellBeing(String token, WellBeingDTO wellbeing) {
        RestClient restClient = RestClient.builder().build();
        return restClient.post()
                .uri("http://localhost:7979/wellbeings")
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(wellbeing)
                .retrieve()
                .body(WellBeingDTO.class);
    }

    public WellBeingDTO updateWellBeing(String token, WellBeingDTO wellbeing, UUID id) {
        RestClient restClient = RestClient.builder().build();
        return restClient.put()
                .uri("http://localhost:7979/wellbeings/update?id=" + id)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(wellbeing)
                .retrieve()
                .body(WellBeingDTO.class);
    }
//
//    public void deleteWellBeing(String token, UUID id) {
//        RestClient restClient = RestClient.builder().build();
//        restClient.delete()
//                .uri("http://localhost:7979/wellbeings/delete?id=" + id)
//                .header("Authorization", "Bearer " + token)
//                .retrieve();
//    }

}
