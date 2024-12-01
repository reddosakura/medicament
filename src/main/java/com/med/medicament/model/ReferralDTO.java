package com.med.medicament.model;

public class ReferralDTO {
    public String id;
    public String reason;
    public AppointmentDTO appoint;
    public DiagnosisDTO diagnosis;

    public ReferralDTO() {
    }

    public ReferralDTO(String id, String reason, AppointmentDTO appoint, DiagnosisDTO diagnosis) {
        this.id = id;
        this.reason = reason;
        this.appoint = appoint;
        this.diagnosis = diagnosis;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public AppointmentDTO getAppoint() {
        return appoint;
    }

    public void setAppoint(AppointmentDTO appoint) {
        this.appoint = appoint;
    }

    public DiagnosisDTO getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(DiagnosisDTO diagnosis) {
        this.diagnosis = diagnosis;
    }
}