package com.gaindesat.ddp.repository;

import com.gaindesat.ddp.models.Parameter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParameterRepository extends CrudRepository<Parameter, UUID> {

    Optional<Parameter> findByName(String name);

}
