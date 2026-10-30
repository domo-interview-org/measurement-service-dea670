package com.homecare.visitbilling.model;

import java.time.LocalDateTime;

public record VisitCompletedEvent(
        String eventId,
        String visitId,
        String patientId,
        String patientName,
        String nurseId,
        CareCategory category,
        LocalDateTime start,
        LocalDateTime end) {
}
