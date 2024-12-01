package com.med.medicament.controllers;


import com.med.medicament.model.AppointmentDTO;
import com.med.medicament.model.ServiceDTO;
import com.med.medicament.service.ServeService;
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
@RequestMapping("service")
public class ServiceController {
    private final ServeService serveService;
    @Value("${token.signing.key}")
    private String jwtSigningKey;

    public ServiceController(ServeService serveService) {
        this.serveService = serveService;
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @GetMapping("/all")
    public String getAllService(HttpServletRequest request, Model model) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        model.addAttribute("services", serveService.getAllServices(token));
        List<String> roles = (List<String>) Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody().get("role");
        model.addAttribute("roles", roles);
        model.addAttribute("servicedto", new ServiceDTO());
        return "service";
    }


    @PostMapping("/update")
    public String updateService(@ModelAttribute("serve") ServiceDTO serviceDTO,
                                @RequestParam UUID id,
                                HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();

        serveService.updateService(token, serviceDTO, id);
        return "redirect:/service/all";
    }


    @PostMapping("/add")
    public String addService(@ModelAttribute("serve") ServiceDTO serve,
                             HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        serveService.addService(token, serve);
        return "redirect:/service/all";
    }


    @PostMapping("/delete")
    public String deleteService(@RequestParam UUID id,
                                HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        serveService.deleteService(token, id);
        return "redirect:/service/all";
    }

}
