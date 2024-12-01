package com.med.medicament.model;


public class DepartmentDTO {
    public String id;
    public String name;

    public DepartmentDTO() {
    }

    public DepartmentDTO(String id, String name) {
        this.id = id;
        this.name = name;
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
}