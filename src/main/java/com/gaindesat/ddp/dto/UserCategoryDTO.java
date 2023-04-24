package com.gaindesat.ddp.dto;

import java.util.UUID;

public class UserCategoryDTO {

    private UUID userUuid;

    private UUID categoryUuid;

    public UUID getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(UUID userUuid) {
        this.userUuid = userUuid;
    }

    public UUID getCategoryUuid() {
        return categoryUuid;
    }

    public void setCategoryUuid(UUID categoryUuid) {
        this.categoryUuid = categoryUuid;
    }
}
