# visit-billing

A home-care organisation bills each nurse visit to the insurer. When a nurse completes a visit,
a `VisitCompletedEvent` arrives from the message broker. `BillingService` turns it into an
invoice line and tells the organisation's coordinator.

Visit times in the event are Swiss local time.

**Your task is in [TASK.md](TASK.md).** New steps are added there during the session: refresh
the page on GitHub, or `git pull`.

## Build and test

Java 17 and Maven:

```
mvn test
```

One test fails at the start (`BillingServiceTest`): step 1 makes it pass.

## Run it (optional)

There is no database and no broker. The repository is in memory, the coordinator notifier only
logs, and a small dev endpoint stands in for the broker:

```
mvn spring-boot:run

curl -X POST localhost:8080/dev/events/visit-completed -H 'Content-Type: application/json' -d '{
  "eventId": "e-1", "visitId": "v-1", "patientId": "p-1", "patientName": "Anna Muster",
  "nurseId": "n-1", "category": "C", "start": "2026-11-03T09:00:00", "end": "2026-11-03T09:30:00"
}'

curl localhost:8080/dev/invoice-lines/v-1
```
