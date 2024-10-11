package com.med.medicament.model;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AppointmentDTO{
    public String id;
    public PatientDTO patient;
    public DoctorDTO doctor;
    public CabinetDTO cabinet;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date date;

    public AppointmentDTO() {
    }

    public AppointmentDTO(String id, PatientDTO patient, DoctorDTO doctor, CabinetDTO cabinet, Date date) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.cabinet = cabinet;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }

    public DoctorDTO getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorDTO doctor) {
        this.doctor = doctor;
    }

    public CabinetDTO getCabinet() {
        return cabinet;
    }

    public void setCabinet(CabinetDTO cabinet) {
        this.cabinet = cabinet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
