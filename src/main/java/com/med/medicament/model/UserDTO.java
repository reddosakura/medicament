package com.med.medicament.model;

import java.util.ArrayList;
import java.util.Date;

public class UserDTO{
    public String id;
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

    public UserDTO(String id, String username, String password, String date_created, ArrayList<String> roles, boolean enabled, boolean accountNonExpired, boolean accountNonLocked, ArrayList<String> authorities, boolean credentialsNonExpired) {
        this.id = id;
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
