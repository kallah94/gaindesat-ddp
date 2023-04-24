package com.gaindesat.ddp.service;


import com.gaindesat.ddp.dto.CategoryDTO;
import com.gaindesat.ddp.models.Category;
import com.gaindesat.ddp.serviceinterface.CategoryServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements CategoryServiceInterface {
    @Override
    public Category populateCategory(CategoryDTO categoryDTO, Category persistenceCategory) {
        persistenceCategory.setCode(categoryDTO.getCode());
        persistenceCategory.setCatName(categoryDTO.getCatName());
        return persistenceCategory;
    }
}
