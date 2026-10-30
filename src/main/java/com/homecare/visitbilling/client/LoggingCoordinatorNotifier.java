package com.homecare.visitbilling.client;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/** Stands in for the real notification service. */
@Slf4j
@Component
public class LoggingCoordinatorNotifier implements CoordinatorNotifier {

    @Override
    public void visitBilled(String visitId) {
        log.info("Coordinator notified: visit {} billed", visitId);
    }
}
