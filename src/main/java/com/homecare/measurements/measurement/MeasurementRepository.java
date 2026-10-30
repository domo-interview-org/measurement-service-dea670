package com.homecare.measurements.measurement;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MeasurementRepository extends MongoRepository<Measurement, String> {
}
