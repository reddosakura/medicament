
package com.med.medicament.controllers;

//import com.med.medicament.model.BulbsIdDTO;
//import com.med.medicament.model.MedCardDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import com.med.medicament.model.*;
import com.med.medicament.service.AdminService;
import com.med.medicament.service.AttachService;
import com.med.medicament.service.MedCardService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("medcards")
public class MedicineCardController {


    private final MedCardService cardService;
    private final AttachService attachService;
    private final AdminService adminService;

    @Value("${token.signing.key}")
    private String jwtSigningKey;
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Autowired
    public MedicineCardController(MedCardService cardService, AttachService attachService, AdminService adminService) {
        this.cardService = cardService;
        this.attachService = attachService;
        this.adminService = adminService;
    }


    @GetMapping("/all")
    public String getAllMedCards(HttpServletRequest request, Model model) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        model.addAttribute("cards", cardService.getAllCards(token));
        List<String> roles = (List<String>) Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody().get("role");
        model.addAttribute("roles", roles);
        return "medcard_list";
    }

    @GetMapping("/create")
    public String createMedCard(HttpServletRequest request, Model model) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();

        model.addAttribute("attachs", attachService.getAllAttach(token));
        model.addAttribute("users", adminService.getAllUsers(token));
        UserDTO user_session = (UserDTO) request.getSession().getAttribute("user");
        model.addAttribute("user_session", user_session);
        List<String> roles = (List<String>) Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody().get("role");
        model.addAttribute("roles", roles);
        model.addAttribute("medcard", new PatientCreateDTO());

        return "create_medcard_detail";
    }


    @PostMapping("/add")
    public String addMedCard(@ModelAttribute("card") PatientCreateDTO card,
                           HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        AttachmentIdDTO attachmentDTO = card.getAttachment();
        UserIdDTO userDTO = card.getUser_account();
        card.setUser_account(userDTO);
        card.setAttachment(attachmentDTO);
        cardService.addCard(token, card);
        return "redirect:/medcards/all";
    }

    @PostMapping("/update")
    public String updateMedCard(@ModelAttribute("card") PatientDTO card,
                              @RequestParam UUID id,
                              HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
//        ArrayList<BulbsIdDTO> bulbs = card.getBulbs();
//        card.setBulbs(bulbs);
//        card.setUser_account(adminService.getUser(token, card.getUser_account().username));

//        card.setAttachment(attachService.getAttach(token, card.getAttachment().id));
        cardService.updateCard(token, card, id);
        return "redirect:/medcards/all";
    }

    @GetMapping("/update")
    public String updateMedCard(
            @RequestParam UUID id,
            HttpServletRequest request, Model model) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        model.addAttribute("medcard", cardService.getCard(token, id));
        model.addAttribute("attachs", attachService.getAllAttach(token));

        List<String> roles = (List<String>) Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody().get("role");
        model.addAttribute("roles", roles);

        return "medcard_detail";
    }

//    @PostMapping("/delete")
//    public String deleteMedCard(@RequestParam UUID id,
//                              HttpServletRequest request) {
//        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
//        cardService.deleteMedCard(token, id);
//        return "redirect:/bulbs/all";
//    }
}
