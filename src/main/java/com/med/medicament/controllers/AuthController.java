package com.med.medicament.controllers;

import com.med.medicament.model.AuthDTO;
import com.med.medicament.model.TokenDTO;
import com.med.medicament.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
//@RequestMapping("")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@ModelAttribute("auth")AuthDTO auth, HttpServletResponse response){
        TokenDTO token = authService.logIn(auth);

        Cookie jwtTokenCookie = new Cookie("access_token", token.getToken());

        jwtTokenCookie.setMaxAge(86400);
        jwtTokenCookie.setHttpOnly(true);
        jwtTokenCookie.setPath("/");
        jwtTokenCookie.setDomain("localhost");
        response.addCookie(jwtTokenCookie);

        return "redirect:/";

    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
