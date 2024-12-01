package com.med.medicament.controllers;


import com.med.medicament.model.DepartmentDTO;
import com.med.medicament.model.DoctorDTO;
import com.med.medicament.service.AdminService;
import com.med.medicament.service.DepartmentService;
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
@RequestMapping("departments")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final AdminService adminService;
    @Value("${token.signing.key}")
    private String jwtSigningKey;

    public DepartmentController(DepartmentService departmentService, AdminService adminService) {
        this.departmentService = departmentService;
        this.adminService = adminService;
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

//    @GetMapping("/all")
//    public String getAllDepartments(HttpServletRequest request, Model model) {
//        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
//        model.addAttribute("departments", departmentService.getAllDepartmentss(token));
//        List<String> roles = (List<String>) Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
//                .getBody().get("role");
//        model.addAttribute("roles", roles);
//        return "departments";
//    }
//
//    @GetMapping("/create")
//    public String createDepartment(HttpServletRequest request, Model model) {
//        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
//
//        model.addAttribute("users", adminService.getAllUsers(token));
//        model.addAttribute("department", new PatientCreateDTO());
//
//        return "department";
//    }
//
//
    @PostMapping("/add")
    public String addDepartment(@ModelAttribute("department") DepartmentDTO department,
                             HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        departmentService.addDepartments(token, department);
        return "redirect:/employes/all";
    }

    @PostMapping("/update")
    public String updateDepartment(@ModelAttribute("department") DepartmentDTO departmentDTO,
                                @RequestParam UUID id,
                                HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();

        departmentService.updateDepartments(token, departmentDTO, id);
        return "redirect:/employes/all";
    }


    @PostMapping("/delete")
    public String deleteDepartment(@RequestParam UUID id,
                                HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        departmentService.deleteDepartments(token, id);
        return "redirect:/employes/all";
    }

}
