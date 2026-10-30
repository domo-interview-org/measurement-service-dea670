package com.homecare.measurements.measurement;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record MeasurementRequest(
        @NotNull MeasurementType type,
        @NotNull BigDecimal value,
        @NotNull String unit) {
}
