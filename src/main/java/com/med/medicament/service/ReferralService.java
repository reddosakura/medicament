package com.med.medicament.service;

//import com.med.medicament.model.ReferralsModel;
//import com.med.medicament.repository.ReferralsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import com.med.medicament.model.ReferralDTO;
import com.med.medicament.model.PatientDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@Service
public class ReferralService {

    public List<ReferralDTO> getAllReferrals(String token) {
        RestClient restClient = RestClient.builder().build();

        return restClient.get()
                .uri("http://localhost:7979/referrals/all")
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public PatientDTO getReferrals(String token, UUID id) {
        RestClient restClient = RestClient.builder().build();

        return restClient.get()
                .uri("http://localhost:7979/referrals/all/" + id)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public ReferralDTO addReferrals(String token, ReferralDTO store) {
        RestClient restClient = RestClient.builder().build();
        return restClient.post()
                .uri("http://localhost:7979/referrals")
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(store)
                .retrieve()
                .body(ReferralDTO.class);
    }

    public PatientDTO updateReferrals(String token, ReferralDTO store, UUID id) {
        RestClient restClient = RestClient.builder().build();
        return restClient.put()
                .uri("http://localhost:7979/referrals/update?id=" + id)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(store)
                .retrieve()
                .body(PatientDTO.class);
    }

    public void deleteReferrals(String token, UUID id) {
        RestClient restClient = RestClient.builder().build();
        restClient.delete()
                .uri("http://localhost:7979/referrals/delete?id=" + id)
                .header("Authorization", "Bearer " + token)
                .retrieve();
    }

}
