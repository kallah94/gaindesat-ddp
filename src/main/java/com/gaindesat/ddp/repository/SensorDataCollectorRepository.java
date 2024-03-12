package com.gaindesat.ddp.repository;

import com.gaindesat.ddp.models.Partner;
import com.gaindesat.ddp.models.SensorDataCollector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SensorDataCollectorRepository extends CrudRepository<SensorDataCollector, UUID> {
    Optional<SensorDataCollector> findByCode(String code);
    Iterable<SensorDataCollector> findSensorDataCollectorByPartnerUuid(UUID partnerUUID);
}
