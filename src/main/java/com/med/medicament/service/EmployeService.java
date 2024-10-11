package com.med.medicament.service;

//import com.med.medicament.model.DoctorModel;
//import com.med.medicament.repository.DoctorRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import com.med.medicament.model.PatientCreateDTO;
import com.med.medicament.model.DoctorDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeService {

    public List<DoctorDTO> getAllDoctors(String token) {
        RestClient restClient = RestClient.builder().build();

        return restClient.get()
                .uri("http://localhost:7979/doctors/all")
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public DoctorDTO getDoctor(String token, UUID id) {
        RestClient restClient = RestClient.builder().build();

        return restClient.get()
                .uri("http://localhost:7979/doctors/all/" + id)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public DoctorDTO addDoctor(String token, DoctorDTO store) {
        RestClient restClient = RestClient.builder().build();
        return restClient.post()
                .uri("http://localhost:7979/doctors")
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(store)
                .retrieve()
                .body(DoctorDTO.class);
    }

    public DoctorDTO updateDoctor(String token, DoctorDTO store, UUID id) {
        RestClient restClient = RestClient.builder().build();
        return restClient.put()
                .uri("http://localhost:7979/doctors/update?id=" + id)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(store)
                .retrieve()
                .body(DoctorDTO.class);
    }

    public void deleteDoctor(String token, UUID id) {
        RestClient restClient = RestClient.builder().build();
        restClient.delete()
                .uri("http://localhost:7979/doctors/delete?id=" + id)
                .header("Authorization", "Bearer " + token)
                .retrieve();
    }

}
