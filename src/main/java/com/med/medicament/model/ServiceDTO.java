package com.med.medicament.model;


import java.util.ArrayList;

public class ServiceDTO{
    public String id;
    public String name;
    public double cost;


    public ServiceDTO() {
    }

    public ServiceDTO(String id, String name, double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
