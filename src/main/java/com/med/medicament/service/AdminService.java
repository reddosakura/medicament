package com.med.medicament.service;

//import com.med.medicament.model.UserModel;
//import com.med.medicament.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import com.med.medicament.model.CreateUserDTO;
import com.med.medicament.model.UserDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@Service
public class AdminService {

    public List<UserDTO> getAllUsers(String token) {
        RestClient restClient = RestClient.builder().build();

        return restClient.get()
                .uri("http://localhost:7979/users/all")
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public UserDTO getUser(String token, String username) {
        RestClient restClient = RestClient.builder().build();

        return restClient.get()
                .uri("http://localhost:7979/users/" + username)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public UserDTO addUser(String token, CreateUserDTO bulb) {
        RestClient restClient = RestClient.builder().build();
        return restClient.post()
                .uri("http://localhost:7979/auth/signup")
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(bulb)
                .retrieve()
                .body(UserDTO.class);
    }

    public CreateUserDTO updateUser(String token, UserDTO bulb) {
        RestClient restClient = RestClient.builder().build();
        return restClient.put()
                .uri("http://localhost:7979/users/update/" + bulb.getId())
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(bulb)
                .retrieve()
                .body(CreateUserDTO.class);
    }

    public void deleteUser(String token, UUID id) {
        RestClient restClient = RestClient.builder().build();
        restClient.delete()
                .uri("http://localhost:7979/users/delete/" + id)
                .header("Authorization", "Bearer " + token)
                .retrieve();
    }

}
