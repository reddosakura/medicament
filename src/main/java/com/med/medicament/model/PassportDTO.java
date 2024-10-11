package com.med.medicament.model;

public class PassportDTO{
    public String id;
    public int series;
    public int number;

    public PassportDTO() {
    }

    public PassportDTO(String id, int series, int number) {
        this.id = id;
        this.series = series;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
