# measurement-service

Backend for a home-care platform: nurses visit patients at home and record their care in a
mobile app. This service owns patients' vital-sign measurements.

## Build

Java 17 and Maven:

```
mvn test
```

## Conventions

- Every endpoint that reads or writes a patient's data checks that the current user is in the
  patient's care team (`PatientAccessService`).
- Logs never contain patient names or other personal data. Log ids only.
- Requirements live in `docs/requirements/`. Tests name the requirement they prove.
