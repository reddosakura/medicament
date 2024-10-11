package com.med.medicament.service;

//import com.med.medicament.model.AppointModel;
//import com.med.medicament.repository.AppointRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import com.med.medicament.model.AppointmentDTO;
import com.med.medicament.model.PatientCreateDTO;
import com.med.medicament.model.PatientDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@Service
public class AppointService {

    public List<PatientDTO> getAllAppoints(String token) {
        RestClient restClient = RestClient.builder().build();

        return restClient.get()
                .uri("http://localhost:7979/appointments/all")
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public List<AppointmentDTO> getAppointsById(String token, String id) {
        RestClient restClient = RestClient.builder().build();

        return restClient.get()
                .uri("http://localhost:7979/appointments/all/" + id)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }


    public AppointmentDTO addAppoint(String token, AppointmentDTO store) {
        RestClient restClient = RestClient.builder().build();
        return restClient.post()
                .uri("http://localhost:7979/appointments")
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(store)
                .retrieve()
                .body(AppointmentDTO.class);
    }

//    public PatientDTO updateAppoint(String token, PatientDTO store, UUID id) {
//        RestClient restClient = RestClient.builder().build();
//        return restClient.put()
//                .uri("http://localhost:7979/appointments/update?id=" + id)
//                .header("Authorization", "Bearer " + token)
//                .header("Content-Type", "application/json")
//                .body(store)
//                .retrieve()
//                .body(PatientDTO.class);
//    }
//
//    public void deleteAppoint(String token, UUID id) {
//        RestClient restClient = RestClient.builder().build();
//        restClient.delete()
//                .uri("http://localhost:7979/stores/delete?id=" + id)
//                .header("Authorization", "Bearer " + token)
//                .retrieve();
//    }

}
