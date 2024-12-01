package com.med.medicament.controllers;


import com.med.medicament.model.*;
import com.med.medicament.service.AdminService;
import com.med.medicament.service.AttachService;
import com.med.medicament.service.DepartmentService;
import com.med.medicament.service.EmployeService;
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
@RequestMapping("employes")
public class EmployeeController {
    private final EmployeService employeeService;
    private final AdminService adminService;
    private final DepartmentService departmentService;
    @Value("${token.signing.key}")
    private String jwtSigningKey;

    public EmployeeController(EmployeService employeeService, AdminService adminService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.adminService = adminService;
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @GetMapping("/all")
    public String getAllEmployes(HttpServletRequest request, Model model) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        model.addAttribute("employees", employeeService.getAllDoctors(token));
        model.addAttribute("users", adminService.getAllUsers(token));
        model.addAttribute("departments", departmentService.getAllDepartments(token));
        List<String> roles = (List<String>) Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody().get("role");
        model.addAttribute("roles", roles);
        model.addAttribute("doctor", new DoctorDTO());
        return "employee";
    }
//
//    @GetMapping("/create")
//    public String createEmploye(HttpServletRequest request, Model model) {
//        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
//
//        model.addAttribute("users", adminService.getAllUsers(token));
//        model.addAttribute("employee", new PatientCreateDTO());
//
//        return "employee";
//    }
//
//
    @PostMapping("/add")
    public String addEmploye(@ModelAttribute("employee") DoctorDTO employee,
                             HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        employeeService.addDoctor(token, employee);
        return "redirect:/employes/all";
    }
//
    @PostMapping("/update")
    public String updateEmploye(@ModelAttribute("employee") DoctorDTO employee,
                                @RequestParam UUID id,
                                HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();

        employeeService.updateDoctor(token, employee, id);
        return "redirect:/employes/all";
    }
//
    @GetMapping("/update")
    public String updateEmploye(
            @RequestParam UUID id,
            HttpServletRequest request, Model model) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        model.addAttribute("empl", employeeService.getDoctor(token, id));

        List<String> roles = (List<String>) Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody().get("role");
        model.addAttribute("roles", roles);

        return "employee_details";
    }
//
    @PostMapping("/delete")
    public String deleteEmploye(@RequestParam UUID id,
                                HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        employeeService.deleteDoctor(token, id);
        return "redirect:/employes/all";
    }

}
