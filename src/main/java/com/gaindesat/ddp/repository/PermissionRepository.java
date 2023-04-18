package com.gaindesat.ddp.repository;

import com.gaindesat.ddp.models.Permission;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PermissionRepository extends CrudRepository<Permission, UUID> { }
