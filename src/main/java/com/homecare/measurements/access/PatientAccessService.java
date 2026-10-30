package com.homecare.measurements.access;

import org.springframework.stereotype.Service;

import com.homecare.measurements.patient.Patient;

@Service
public class PatientAccessService {

    public void assertCanAccess(String userId, Patient patient) {
        if (userId == null || !patient.getCareTeam().contains(userId)) {
            throw new AccessDeniedException("User may not access patient " + patient.getId());
        }
    }
}
