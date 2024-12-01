package com.med.medicament.model;


public class DoctorDTO{
    public String id;
    public int tabelNumber;
    public String speciality;
    public UserDTO account;
    public DepartmentDTO department;



    public DoctorDTO() {
    }

    public DoctorDTO(String id, int tabelNumber, String speciality, UserDTO account, DepartmentDTO department) {
        this.id = id;
        this.tabelNumber = tabelNumber;
        this.speciality = speciality;
        this.account = account;
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public DepartmentDTO getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDTO department) {
        this.department = department;
    }
}