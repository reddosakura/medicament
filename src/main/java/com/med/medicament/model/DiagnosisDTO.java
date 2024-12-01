package com.med.medicament.model;

import java.util.UUID;

public class DiagnosisDTO {
    public String id;
    public String name;
    public String icd;

    public DiagnosisDTO() {
    }

    public DiagnosisDTO(String id, String name, String icd) {
        this.id = id;
        this.name = name;
        this.icd = icd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcd() {
        return icd;
    }

    public void setIcd(String icd) {
        this.icd = icd;
    }
}
