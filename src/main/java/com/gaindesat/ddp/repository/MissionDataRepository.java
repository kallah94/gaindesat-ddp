package com.gaindesat.ddp.repository;

import com.gaindesat.ddp.models.MissionData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MissionDataRepository extends CrudRepository<MissionData, UUID> {

    Optional<MissionData> findByParameter(String parameter);
}
