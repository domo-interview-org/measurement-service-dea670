package com.homecare.measurements.patient;

public record PatientResponse(String id, String name) {

    static PatientResponse from(Patient patient) {
        return new PatientResponse(patient.getId(), patient.getName());
    }
}
