package com.med.medicament.model;

import java.util.ArrayList;
import java.util.Date;

public class UserDTO{
    public String id;
    public String lastname;
    public String name;
    public String patronymic;
    public String phone_number;
    public String email;
    public String username;
    public String password;
    public String date_created;
    public ArrayList<String> roles;
    public boolean enabled;
    public boolean accountNonExpired;
    public boolean accountNonLocked;
    public ArrayList<String> authorities;
    public boolean credentialsNonExpired;

    public UserDTO() {
    }

    public UserDTO(String id, String lastname, String name, String patronymic, String phone_number, String email, String username, String password, String date_created, ArrayList<String> roles, boolean enabled, boolean accountNonExpired, boolean accountNonLocked, ArrayList<String> authorities, boolean credentialsNonExpired) {
        this.id = id;
        this.lastname = lastname;
        this.name = name;
        this.patronymic = patronymic;
        this.phone_number = phone_number;
        this.email = email;
        this.username = username;
        this.password = password;
        this.date_created = date_created;
        this.roles = roles;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.authorities = authorities;
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public ArrayList<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(ArrayList<String> authorities) {
        this.authorities = authorities;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }
}
