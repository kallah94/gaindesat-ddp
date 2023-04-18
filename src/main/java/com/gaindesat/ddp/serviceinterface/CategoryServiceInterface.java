package com.gaindesat.ddp.serviceinterface;

import com.gaindesat.ddp.dto.CategoryDTO;
import com.gaindesat.ddp.models.Category;

public interface CategoryServiceInterface {

    Category populateCategory(CategoryDTO categoryDTO, Category category);
}
