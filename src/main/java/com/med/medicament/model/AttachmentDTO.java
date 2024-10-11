package com.med.medicament.model;

import java.util.ArrayList;

public class AttachmentDTO{
    public String id;
    public String type_name;
    public ArrayList<PatientDTO> patients;

    public AttachmentDTO() {
    }

    public AttachmentDTO(String id, String type_name, ArrayList<PatientDTO> patients) {
        this.id = id;
        this.type_name = type_name;
        this.patients = patients;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public ArrayList<PatientDTO> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<PatientDTO> patients) {
        this.patients = patients;
    }
}