package com.med.medicament.controllers;

import com.med.medicament.model.DiagnosisDTO;
import com.med.medicament.service.AdminService;
import com.med.medicament.service.DiagnosisService;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import java.security.Key;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("diagnosis")
public class DiagnosisController {
    private final DiagnosisService diagnosisService;
    private final AdminService adminService;
    @Value("${token.signing.key}")
    private String jwtSigningKey;

    public DiagnosisController(DiagnosisService diagnosisService, AdminService adminService) {
        this.diagnosisService = diagnosisService;
        this.adminService = adminService;
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @PostMapping("/add")
    public String addDiagnosis(@ModelAttribute("diagnosis") DiagnosisDTO diagnosis,
                                HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        diagnosisService.addDiagnosis(token, diagnosis);
        return "redirect:/referrals/all";
    }

    @PostMapping("/update")
    public String updateDiagnosis(@ModelAttribute("diagnosis") DiagnosisDTO diagnosisDTO,
                                   @RequestParam UUID id,
                                   HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();

        diagnosisService.updateDiagnosis(token, diagnosisDTO, id);
        return "redirect:/referrals/all";
    }


    @PostMapping("/delete")
    public String deleteDiagnosis(@RequestParam UUID id,
                                   HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        diagnosisService.deleteDiagnosis(token, id);
        return "redirect:/referrals/all";
    }
}
