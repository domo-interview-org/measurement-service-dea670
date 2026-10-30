package com.homecare.visitbilling.dev;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.homecare.visitbilling.model.InvoiceLine;
import com.homecare.visitbilling.model.VisitCompletedEvent;
import com.homecare.visitbilling.repository.InvoiceLineRepository;
import com.homecare.visitbilling.service.BillingService;

/** Stands in for the message broker, so the service can be tried by hand. Not part of the product. */
@RestController
@RequestMapping("/dev")
@RequiredArgsConstructor
public class DevEventController {

    private final BillingService billingService;
    private final InvoiceLineRepository invoiceLines;

    @PostMapping("/events/visit-completed")
    public ResponseEntity<Void> visitCompleted(@RequestBody VisitCompletedEvent event) {
        billingService.onVisitCompleted(event);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/invoice-lines/{visitId}")
    public ResponseEntity<InvoiceLine> invoiceLine(@PathVariable String visitId) {
        return ResponseEntity.of(invoiceLines.findByVisitId(visitId));
    }
}
