package com.homecare.visitbilling.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.homecare.visitbilling.model.InvoiceLine;

/** Stands in for the database. */
@Repository
public class InMemoryInvoiceLineRepository implements InvoiceLineRepository {

    private final Map<String, InvoiceLine> lines = new HashMap<>();

    @Override
    public void save(InvoiceLine line) {
        lines.put(line.visitId(), line);
    }

    @Override
    public Optional<InvoiceLine> findByVisitId(String visitId) {
        return Optional.ofNullable(lines.get(visitId));
    }
}
