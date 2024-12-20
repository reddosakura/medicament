package com.med.medicament.controllers;


import com.med.medicament.model.CabinetDTO;
import com.med.medicament.model.DoctorDTO;
import com.med.medicament.service.AdminService;
import com.med.medicament.service.CabinetService;
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
@RequestMapping("cabinets")
public class CabinetController {
    private final CabinetService cabinetService;
    private final AdminService adminService;
    @Value("${token.signing.key}")
    private String jwtSigningKey;

    public CabinetController(CabinetService cabinetService, AdminService adminService) {
        this.cabinetService = cabinetService;
        this.adminService = adminService;
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @GetMapping("/all")
    public String getAllCabinets(HttpServletRequest request, Model model) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        model.addAttribute("cabinets", cabinetService.getAllCabinetss(token));
        List<String> roles = (List<String>) Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody().get("role");
        model.addAttribute("roles", roles);
        return "cabinets";
    }
//
//    @GetMapping("/create")
//    public String createCabinet(HttpServletRequest request, Model model) {
//        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
//
//        model.addAttribute("users", adminService.getAllUsers(token));
//        model.addAttribute("cabinet", new PatientCreateDTO());
//
//        return "cabinet";
//    }
//
//
    @PostMapping("/add")
    public String addCabinet(@ModelAttribute("cabinet") CabinetDTO cabinet,
                             HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        cabinetService.addCabinets(token, cabinet);
        return "redirect:/cabinets/all";
    }


    @PostMapping("/delete")
    public String deleteCabinet(@RequestParam UUID id,
                                HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        cabinetService.deleteCabinets(token, id);
        return "redirect:/cabinets/all";
    }

}
