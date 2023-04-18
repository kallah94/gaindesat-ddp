package com.gaindesat.ddp.repository;

import com.gaindesat.ddp.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends CrudRepository<Category, UUID> {
    Optional<Category> findByCode(String code);
}
