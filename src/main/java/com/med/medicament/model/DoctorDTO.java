package com.med.medicament.model;


public class DoctorDTO{
    public String id;
    public String lastname;
    public String name;
    public String patronymic;
    public String phone_number;
    public String emaildoc;
    public int tabelNumber;
    public String speciality;
    public UserDTO account;

    public DoctorDTO(String id, String lastname, String name, String patronymic, String phone_number, String emaildoc, int tabelNumber, String speciality, UserDTO account) {
        this.id = id;
        this.lastname = lastname;
        this.name = name;
        this.patronymic = patronymic;
        this.phone_number = phone_number;
        this.emaildoc = emaildoc;
        this.tabelNumber = tabelNumber;
        this.speciality = speciality;
        this.account = account;
    }

    public DoctorDTO() {
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

    public int getTabelNumber() {
        return tabelNumber;
    }

    public void setTabelNumber(int tabelNumber) {
        this.tabelNumber = tabelNumber;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public UserDTO getAccount() {
        return account;
    }

    public void setAccount(UserDTO account) {
        this.account = account;
    }
}