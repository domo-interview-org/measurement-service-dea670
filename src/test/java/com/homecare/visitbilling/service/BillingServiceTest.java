package com.homecare.visitbilling.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.homecare.visitbilling.client.CoordinatorNotifier;
import com.homecare.visitbilling.model.CareCategory;
import com.homecare.visitbilling.model.InvoiceLine;
import com.homecare.visitbilling.model.Tariff;
import com.homecare.visitbilling.model.VisitCompletedEvent;
import com.homecare.visitbilling.repository.InMemoryInvoiceLineRepository;

class BillingServiceTest {

    private final InMemoryInvoiceLineRepository invoiceLines = new InMemoryInvoiceLineRepository();
    private final CoordinatorNotifier notifier = mock(CoordinatorNotifier.class);
    private final BillingService billing = new BillingService(Tariff.standard(), invoiceLines, notifier);

    @Test
    void billsAThirtyMinuteCategoryCVisit() {
        billing.onVisitCompleted(new VisitCompletedEvent(
                "e-1", "v-1", "p-1", "Anna Muster", "n-1", CareCategory.C,
                LocalDateTime.of(2026, 11, 3, 9, 0),
                LocalDateTime.of(2026, 11, 3, 9, 30)));

        InvoiceLine line = invoiceLines.findByVisitId("v-1").orElseThrow();
        assertThat(line.billedMinutes()).isEqualTo(30);
        assertThat(line.amount()).isEqualByComparingTo("27.50");
        verify(notifier).visitBilled("v-1");
    }
}
