package com.med.medicament.model;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;

public class PatientDTO{
    public String id;
    public String lastname;
    public String name;
    public String patronymic;
    public String phone_number;
    public String emaildoc;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date bith_date;
    public String citizenship;
    public String gender;
    public int height;
    public int weight;
    public int snils;
    public PassportDTO passport;
    public UserDTO user_account;
    public AttachmentDTO attachment;
    public ArrayList<AppointmentDTO> appoints;

    public PatientDTO() {
    }

    public PatientDTO(String id, String lastname, String name, String patronymic, String phone_number, String emaildoc, Date bith_date, String citizenship, String gender, int height, int weight, int snils, PassportDTO passport, UserDTO user_account, AttachmentDTO attachment, ArrayList<AppointmentDTO> appoints) {
        this.id = id;
        this.lastname = lastname;
        this.name = name;
        this.patronymic = patronymic;
        this.phone_number = phone_number;
        this.emaildoc = emaildoc;
        this.bith_date = bith_date;
        this.citizenship = citizenship;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.snils = snils;
        this.passport = passport;
        this.user_account = user_account;
        this.attachment = attachment;
        this.appoints = appoints;
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

    public String getEmaildoc() {
        return emaildoc;
    }

    public void setEmaildoc(String emaildoc) {
        this.emaildoc = emaildoc;
    }

    public Date getBith_date() {
        return bith_date;
    }

    public void setBith_date(Date bith_date) {
        this.bith_date = bith_date;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSnils() {
        return snils;
    }

    public void setSnils(int snils) {
        this.snils = snils;
    }

    public PassportDTO getPassport() {
        return passport;
    }

    public void setPassport(PassportDTO passport) {
        this.passport = passport;
    }

    public UserDTO getUser_account() {
        return user_account;
    }

    public void setUser_account(UserDTO user_account) {
        this.user_account = user_account;
    }

    public AttachmentDTO getAttachment() {
        return attachment;
    }

    public void setAttachment(AttachmentDTO attachment) {
        this.attachment = attachment;
    }

    public ArrayList<AppointmentDTO> getAppoints() {
        return appoints;
    }

    public void setAppoints(ArrayList<AppointmentDTO> appoints) {
        this.appoints = appoints;
    }
}

