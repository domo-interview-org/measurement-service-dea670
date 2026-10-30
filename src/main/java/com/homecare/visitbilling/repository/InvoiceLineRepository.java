package com.homecare.visitbilling.repository;

import java.util.Optional;

import com.homecare.visitbilling.model.InvoiceLine;

public interface InvoiceLineRepository {

    void save(InvoiceLine line);

    Optional<InvoiceLine> findByVisitId(String visitId);
}
