package com.med.medicament.controllers;

//import com.med.medicament.model.BulbModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.WebUtils;

import java.security.Key;
import java.util.List;
import java.util.Objects;

@Controller
public class HomeController {

    @Value("${token.signing.key}")
    private String jwtSigningKey;
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @GetMapping("/")
    public String getHome(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, "access_token");
        if (cookie != null) {
            String token = cookie.getValue();
            List<String> roles = (List<String>) Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                    .getBody().get("role");

            if (roles.contains("ADMIN")) {
                return "redirect:/admin/users/all";
            } else if (roles.contains("USER") || roles.contains("ADMIN")) {
                return "redirect:/appoints/all";
            } else if (roles.contains("DOCTOR") || roles.contains("ADMIN")) {
                return "redirect:/medcards/all";
            }
        }

        return "redirect:/login";
    }
}
