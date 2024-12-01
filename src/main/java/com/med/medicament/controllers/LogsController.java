package com.med.medicament.controllers;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("logs")
public class LogsController {

    @Value("${token.signing.key}")
    private String jwtSigningKey;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @GetMapping("/")
    public String getAllEmployes(HttpServletRequest request, Model model) throws IOException {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        List<String> roles = (List<String>) Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody().get("role");
        model.addAttribute("roles", roles);
        model.addAttribute("log", String.join("\n", Files.readAllLines(Paths.get("/Users/reddosakura/IdeaProjects/Medicament/logs/spring-boot-logger.log"))));
//        model.addAttribute("log", Files.readAllLines(Paths.get("/Users/reddosakura/IdeaProjects/Medicament/logs/spring-boot-logger.log")));
        return "system";
    }
}
