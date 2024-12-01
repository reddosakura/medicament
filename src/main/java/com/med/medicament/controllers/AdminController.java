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

import java.io.*;
import java.security.Key;
import java.util.*;

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

    @PostMapping("/search")
    public String getUser(@RequestParam("username") String username, HttpServletRequest request, Model model) {
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

    @PostMapping("/filter")
    public String filterUsers(@RequestParam("role_filter") String role_filter, Model model, HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        List<UserDTO> users = adminService.filterUsers(token, role_filter);
        System.out.println(users);
        model.addAttribute("users", users);
        model.addAttribute("user", new CreateUserDTO());
        List<String> roles = (List<String>) Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody().get("role");
        System.out.println(roles);
        model.addAttribute("roles", roles);
        return "admin_page";
    }

    @GetMapping("/sort/desc")
    public String sortUsersDESC(Model model, HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        List<UserDTO> users = adminService.sortUsersDESC(token);
        System.out.println(users);
        model.addAttribute("users", users);
        model.addAttribute("user", new CreateUserDTO());
        List<String> roles = (List<String>) Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody().get("role");
        System.out.println(roles);
        model.addAttribute("roles", roles);
        return "admin_page";
    }

    @GetMapping("/sort/asc")
    public String sortUsersASC(Model model, HttpServletRequest request) {
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        List<UserDTO> users = adminService.sortUsersASC(token);
        System.out.println(users);
        model.addAttribute("users", users);
        model.addAttribute("user", new CreateUserDTO());
        List<String> roles = (List<String>) Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody().get("role");
        System.out.println(roles);
        model.addAttribute("roles", roles);
        return "admin_page";
    }

    @PostMapping("/backup")
    public String getBackup() throws IOException, InterruptedException {

        String[] command = String.format("./Contents/SharedSupport/pg_dump --file=./backups/%s --host=localhost --port=9800 --username=postgres -w --format=d --verbose medicamentdb", "backup_v" + System.currentTimeMillis()).split(" ");

        ProcessBuilder builder = new ProcessBuilder();
        builder.command(command);
        builder.directory(new File(System.getProperty("user.dir")));
        Process process = builder.start();

        BufferedReader in = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }

        process.waitFor();
        in.close();
        return "redirect:/admin/users/restore";
    }

    @GetMapping("/restore")
    public String getRestore(HttpServletRequest request, Model model) throws IOException, InterruptedException {

        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        List<String> roles = (List<String>) Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody().get("role");
        model.addAttribute("roles", roles);

        File file = new File("./backups");
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        model.addAttribute("directories", directories);

        return "restore";
    }

    @PostMapping("/restore")
    public String postRestore(@RequestParam("versions") String version, HttpServletRequest request, Model model) throws IOException, InterruptedException {
        System.out.println(version);
        String token = Objects.requireNonNull(WebUtils.getCookie(request, "access_token")).getValue();
        List<String> roles = (List<String>) Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody().get("role");
        model.addAttribute("roles", roles);



        String[] command = String.format("./Contents/SharedSupport/pg_restore --host=localhost --port=9800 --username=postgres --no-password --dbname medicamentdb --format=d --clean --verbose ./backups/%s", version).split(" ");

        ProcessBuilder builder = new ProcessBuilder();
        builder.command(command);
        builder.directory(new File(System.getProperty("user.dir")));
        Process process = builder.start();

        BufferedReader in = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }

        process.waitFor();
        in.close();

        return "redirect:/admin/users/all";
    }
}
