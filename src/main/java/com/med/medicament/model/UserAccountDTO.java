package com.med.medicament.model;

import java.util.ArrayList;

public class UserAccountDTO{
    public String id;
    public String username;
    public String password;
    public String date_created;
    public ArrayList<String> roles;
    public boolean enabled;
    public ArrayList<String> authorities;
    public boolean accountNonExpired;
    public boolean accountNonLocked;
    public boolean credentialsNonExpired;
}
