package com.homecare.visitbilling.model;

import java.math.BigDecimal;

public record InvoiceLine(String visitId, CareCategory category, long billedMinutes, BigDecimal amount) {
}
