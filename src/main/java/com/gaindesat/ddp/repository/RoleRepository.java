package com.gaindesat.ddp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gaindesat.ddp.models.ERole;
import com.gaindesat.ddp.models.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
