package com.gaindesat.ddp.repository;

import com.gaindesat.ddp.models.SensorDataCollector;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface SensorDataCollectorRepository extends CrudRepository<SensorDataCollector, UUID> {
    Optional<SensorDataCollector> findByCode(String code);
}
