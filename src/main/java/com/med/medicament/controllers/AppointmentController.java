package com.med.medicament.controllers;


import com.med.medicament.model.AppointmentDTO;
import com.med.medicament.model.DoctorDTO;
import com.med.medicament.service.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import java.security.Key;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("appoints")
public class AppointmentController {
    private final AppointService appointService;
    private final EmployeService employeService;
    private final CabinetService cabinetService;
    private final MedCardService medCardService;
//    private final AdminService adminService;
    @Value("${token.signing.key}")
    private String jwtSigningKey;

    public AppointmentController(AppointService appointService, EmployeService employeService, CabinetService cabinetService, MedCardService medCardService) {
        this.appointService = appointService;
//        this.adminService = adminService;
        this.employeService = employeService;
        this.cabinetService = cabinetService;
        this.medCardService = medCardService;
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @GetMapping("/all")
    public String getAllAppoint(HttpServletRequest request, Model model) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        List<String> roles = (List<String>) Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody().get("role");
        String user_id = (String) Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody().get("id");

        System.out.println(user_id);
        System.out.println(roles);
        model.addAttribute("appoints", appointService.getAppointsById(token, medCardService.getCardByUserID(token, UUID.fromString(user_id)).id));
        model.addAttribute("patient", medCardService.getCardByUserID(token, UUID.fromString(user_id)));
        model.addAttribute("cabinets", cabinetService.getAllCabinetss(token));
        model.addAttribute("doctors", employeService.getAllDoctors(token));
        model.addAttribute("appointdto", new AppointmentDTO());
        model.addAttribute("roles", roles);

        return "doctor_appointment";
    }

    @PostMapping("/add")
    public String addAppoint(@ModelAttribute("appoint") AppointmentDTO appoint,
                             HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        appointService.addAppoint(token, appoint);
        return "redirect:/appoints/all";
    }
}
