package com.homecare.measurements.measurement;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MeasurementController {

    private final MeasurementService measurementService;

    @PostMapping("/patients/{patientId}/measurements")
    public ResponseEntity<Measurement> record(@PathVariable String patientId,
                                              @Valid @RequestBody MeasurementRequest request) {
        Measurement saved = measurementService.record(patientId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
