package com.homecare.measurements.patient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.homecare.measurements.access.CurrentUser;
import com.homecare.measurements.access.PatientAccessService;
import com.homecare.measurements.web.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PatientController {

    private final PatientRepository patientRepository;
    private final PatientAccessService patientAccessService;
    private final CurrentUser currentUser;

    @GetMapping("/patients/{patientId}")
    public PatientResponse get(@PathVariable String patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NotFoundException("Patient not found: " + patientId));
        patientAccessService.assertCanAccess(currentUser.userId(), patient);
        log.debug("Patient {} read by user {}", patientId, currentUser.userId());
        return PatientResponse.from(patient);
    }
}
