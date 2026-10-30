package com.homecare.visitbilling.model;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public final class Tariff {

    private final Map<CareCategory, BigDecimal> hourlyRates;

    public Tariff(Map<CareCategory, BigDecimal> hourlyRates) {
        this.hourlyRates = Map.copyOf(hourlyRates);
    }

    public static Tariff standard() {
        return new Tariff(Map.of(
                CareCategory.A, new BigDecimal("80.00"),
                CareCategory.B, new BigDecimal("70.00"),
                CareCategory.C, new BigDecimal("55.00")));
    }

    /** The rate in CHF per hour, or empty if this tariff has none for the category. */
    public Optional<BigDecimal> hourlyRate(CareCategory category) {
        return Optional.ofNullable(hourlyRates.get(category));
    }
}
