package com.med.medicament.controllers;

import com.med.medicament.model.CreateUserDTO;
import com.med.medicament.model.UserDTO;
import com.med.medicament.service.AdminService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("admin/users")
public class AdminController {

    private final AdminService adminService;

    @Value("${token.signing.key}")
    private String jwtSigningKey;
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }



    @GetMapping("/all")
    public String getAllBulbs(HttpServletRequest request, Model model) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        List<UserDTO> users = adminService.getAllUsers(token);
        System.out.println(users);
        model.addAttribute("users", users);
        model.addAttribute("user", new CreateUserDTO());
        List<String> roles = (List<String>) Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody().get("role");
        System.out.println(roles);
        model.addAttribute("roles", roles);
        return "admin_page";
    }

    @GetMapping("/{username}")
    public String getUser(@PathVariable String username, HttpServletRequest request, Model model) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        UserDTO user = adminService.getUser(token, username);
        model.addAttribute("user", user);
        List<String> roles = (List<String>) Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody().get("role");
        model.addAttribute("roles", roles);
        return "user_details";
    }

    @PostMapping("/add")
    public String addBulb(@ModelAttribute("bulb") CreateUserDTO bulb,
                          HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        adminService.addUser(token, bulb);
        return "redirect:/admin/users/all";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("bulb") UserDTO bulb,
                          HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        adminService.updateUser(token, bulb);
        return "redirect:/admin/users/all";
    }

    @PostMapping("/delete")
    public String deleteBulb(@RequestParam UUID id,
                             HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        adminService.deleteUser(token, id);
        return "redirect:/admin/users/all";
    }
}
