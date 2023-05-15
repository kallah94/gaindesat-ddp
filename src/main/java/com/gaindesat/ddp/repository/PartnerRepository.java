package com.gaindesat.ddp.repository;


import com.gaindesat.ddp.dto.PartnerDTO;
import com.gaindesat.ddp.models.Partner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PartnerRepository extends CrudRepository<Partner, UUID> {
    Optional<Partner> findByCode(String code);
}
