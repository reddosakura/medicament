package com.med.medicament.controllers;

import com.med.medicament.model.PatientDTO;
import com.med.medicament.model.WellBeingDTO;
import com.med.medicament.service.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;
//import java.time.format.DateTimeFormatter;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("wellbeing")
public class WellBeingController {
    private final MedCardService medCardService;
    private final WellBeingService wellbeingService;
    @Value("${token.signing.key}")
    private String jwtSigningKey;

    public WellBeingController(MedCardService medCardService, WellBeingService wellBeingService) {
        this.medCardService = medCardService;
        this.wellbeingService = wellBeingService;
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @GetMapping("/diary")
    public String getAllWellBeings(HttpServletRequest request, Model model) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        List<String> roles = (List<String>) Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody().get("role");
        model.addAttribute("roles", roles);

        String user_id = (String) Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody().get("id");
        PatientDTO patient = medCardService.getCardByUserID(token, UUID.fromString(user_id));
        List<WellBeingDTO> wellbeings = wellbeingService.getWellBeingsById(token, patient.getId());
        List<Integer> grades = wellbeings.stream().map(WellBeingDTO::getGrade).toList();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        List<String> dates = wellbeings.stream().map(s -> formatter.format(s.getDate())).toList();
        List<String> desc = wellbeings.stream().map(WellBeingDTO::getDescription).toList();

        String desc_string = "[\"" + String.join("\",\"", desc) + "\"]";

        String dates_string = "[\"" + String.join("\", \"", dates) + "\"]";

        model.addAttribute("grades", grades);
        model.addAttribute("dates", dates_string);
        model.addAttribute("desc", desc_string);
        model.addAttribute("patient", patient.getId());
        model.addAttribute("wellbeing", new WellBeingDTO());

        System.out.println(dates_string);
        return "wellbeing_diary";
    }

    @PostMapping("/add")
    public String addWellBeing(@ModelAttribute("wellbeing") WellBeingDTO wellBeingDTO,
                               HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        wellbeingService.addWellBeing(token, wellBeingDTO);
        return "redirect:/wellbeing/diary";
    }

    @PostMapping("/update")
    public String updateWellBeing(@ModelAttribute("wellbeing") WellBeingDTO diagnosisDTO,
                                  @RequestParam UUID id,
                                  HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();

        wellbeingService.updateWellBeing(token, diagnosisDTO, id);
        return "redirect:/wellbeings/all";
    }


//    @PostMapping("/delete")
//    public String deleteWellBeing(@RequestParam UUID id,
//                                  HttpServletRequest request) {
//        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
//        wellbeingService.deleteWellBeings(token, id);
//        return "redirect:/wellbeings/all";
//    }
}
