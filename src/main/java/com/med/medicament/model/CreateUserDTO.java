package com.med.medicament.model;

import java.util.List;

public class CreateUserDTO {
    public String lastname;
    public String name;
    public String patronymic;
    public String phone_number;
    public String email;
    private String username;
    private String password;
    private List<String> roles;

    public CreateUserDTO() {
    }

    public CreateUserDTO(String lastname, String name, String patronymic, String phone_number, String email, String username, String password, List<String> roles) {
        this.lastname = lastname;
        this.name = name;
        this.patronymic = patronymic;
        this.phone_number = phone_number;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
