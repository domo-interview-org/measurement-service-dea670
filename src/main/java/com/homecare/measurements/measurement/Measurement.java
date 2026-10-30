package com.homecare.measurements.measurement;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("measurements")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Measurement {

    @Id
    private String id;
    private String patientId;
    private MeasurementType type;
    private BigDecimal value;
    private String unit;
    private String source = "NURSE_APP";
    private LocalDateTime measuredAt;
    private String createdBy;
    private LocalDateTime createdAt;
}
