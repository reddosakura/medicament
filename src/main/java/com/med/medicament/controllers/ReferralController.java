package com.med.medicament.controllers;

import com.med.medicament.model.DiagnosisDTO;
import com.med.medicament.model.DoctorDTO;
import com.med.medicament.model.ReferralDTO;
import com.med.medicament.service.AdminService;
import com.med.medicament.service.AppointService;
import com.med.medicament.service.ReferralService;
import com.med.medicament.service.DiagnosisService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import java.security.Key;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("referrals")
public class ReferralController {
    private final DiagnosisService diagnosisService;
    private final AppointService appointService;
    private final ReferralService referralService;
    @Value("${token.signing.key}")
    private String jwtSigningKey;

    public ReferralController(DiagnosisService diagnosisService, AppointService appointService, ReferralService referralService) {
        this.diagnosisService = diagnosisService;
        this.referralService = referralService;
        this.appointService = appointService;
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @GetMapping("/all")
    public String getAllReferrals(HttpServletRequest request, Model model) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        model.addAttribute("diagnosis", diagnosisService.getAllDiagnosis(token));
        model.addAttribute("appoints", appointService.getAllAppoints(token));
//        model.addAttribute("users", adminService.getAllUsers(token));
        model.addAttribute("referrals", referralService.getAllReferrals(token));
        List<String> roles = (List<String>) Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody().get("role");
        model.addAttribute("roles", roles);
        model.addAttribute("referral", new ReferralDTO());
        return "referral";
    }

    @PostMapping("/add")
    public String addReferral(@ModelAttribute("referral") ReferralDTO diagnosis,
                               HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        referralService.addReferrals(token, diagnosis);
        return "redirect:/referrals/all";
    }

    @PostMapping("/update")
    public String updateReferral(@ModelAttribute("referral") ReferralDTO diagnosisDTO,
                                  @RequestParam UUID id,
                                  HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();

        referralService.updateReferrals(token, diagnosisDTO, id);
        return "redirect:/referrals/all";
    }


    @PostMapping("/delete")
    public String deleteReferral(@RequestParam UUID id,
                                  HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        referralService.deleteReferrals(token, id);
        return "redirect:/referrals/all";
    }
}
