package com.homecare.visitbilling.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.homecare.visitbilling.client.CoordinatorNotifier;
import com.homecare.visitbilling.model.Tariff;
import com.homecare.visitbilling.model.VisitCompletedEvent;
import com.homecare.visitbilling.repository.InvoiceLineRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class BillingService {

    private final Tariff tariff;
    private final InvoiceLineRepository invoiceLines;
    private final CoordinatorNotifier notifier;

    public void onVisitCompleted(VisitCompletedEvent event) {
        log.info("Billing visit for patient " + event.patientName());
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
