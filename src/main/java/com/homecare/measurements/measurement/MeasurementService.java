package com.homecare.measurements.measurement;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.homecare.measurements.access.CurrentUser;
import com.homecare.measurements.patient.Patient;
import com.homecare.measurements.patient.PatientRepository;
import com.homecare.measurements.web.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MeasurementService {

    private final PatientRepository patientRepository;
    private final MeasurementRepository measurementRepository;
    private final CurrentUser currentUser;

    public Measurement record(String patientId, MeasurementRequest request) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NotFoundException("Patient not found: " + patientId));

        log.info("Recording " + request.type() + " measurement for " + patient.getName()
                + " (" + patientId + ")");

        String unit = request.unit().trim();
        switch (request.type()) {
            case TEMPERATURE -> {
                if (!unit.equals("°C")) {
                    throw new IllegalArgumentException("Temperature must be in °C");
                }
            }
            case PULSE -> {
                if (!unit.equals("bpm")) {
                    throw new IllegalArgumentException("Pulse must be in bpm");
                }
            }
            case systolik -> {
                if (!unit.equals("mmHg")) {
                    throw new IllegalArgumentException("Systolic pressure must be in mmHg");
                }
            }
        }

        Measurement measurement = Measurement.builder()
                .patientId(patient.getId())
                .type(request.type())
                .value(request.value())
                .unit(unit)
                .build();
        measurement.setMeasuredAt(LocalDateTime.now());
        measurement.setCreatedBy(currentUser.userId());
        measurement.setCreatedAt(LocalDateTime.now());

        Measurement saved = measurementRepository.save(measurement);
        log.info("Saved measurement " + saved.getId());
        return saved;
    }
}
