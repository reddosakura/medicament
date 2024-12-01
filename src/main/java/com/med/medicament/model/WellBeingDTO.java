package com.med.medicament.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class WellBeingDTO {
    public String id;
    public int grade;
    public String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date date;
    public PatientDTO patient;

    public WellBeingDTO() {
    }

    public WellBeingDTO(String id, int grade, String description, Date date, PatientDTO patient) {
        this.id = id;
        this.grade = grade;
        this.description = description;
        this.date = date;
        this.patient = patient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }
}