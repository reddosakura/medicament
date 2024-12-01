package com.med.medicament.service;

//import com.med.medicament.model.DepartmentsModel;
//import com.med.medicament.repository.DepartmentsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import com.med.medicament.model.DepartmentDTO;
import com.med.medicament.model.PatientDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@Service
public class DepartmentService {

    public List<DepartmentDTO> getAllDepartments(String token) {
        RestClient restClient = RestClient.builder().build();

        return restClient.get()
                .uri("http://localhost:7979/departments/all")
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public PatientDTO getDepartments(String token, UUID id) {
        RestClient restClient = RestClient.builder().build();

        return restClient.get()
                .uri("http://localhost:7979/departments/all/" + id)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public DepartmentDTO addDepartments(String token, DepartmentDTO store) {
        RestClient restClient = RestClient.builder().build();
        return restClient.post()
                .uri("http://localhost:7979/departments")
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(store)
                .retrieve()
                .body(DepartmentDTO.class);
    }

    public PatientDTO updateDepartments(String token, DepartmentDTO store, UUID id) {
        RestClient restClient = RestClient.builder().build();
        return restClient.put()
                .uri("http://localhost:7979/departments/update?id=" + id)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(store)
                .retrieve()
                .body(PatientDTO.class);
    }

    public void deleteDepartments(String token, UUID id) {
        RestClient restClient = RestClient.builder().build();
        restClient.delete()
                .uri("http://localhost:7979/departments/delete?id=" + id)
                .header("Authorization", "Bearer " + token)
                .retrieve();
    }

}
