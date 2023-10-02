package com.gaindesat.ddp.dto;

import java.util.UUID;

public final class PermissionDTO {
    private UUID uuid;
    private String code;
    private String title;

    private String categoryName;
    private UUID categoryUUID;

    public PermissionDTO(UUID uuid, String code, String title, UUID categoryUUID, String categoryName) {
        this.uuid = uuid;
        this.code = code;
        this.title = title;
        this.categoryUUID = categoryUUID;
        this.categoryName = categoryName;
    }

    public PermissionDTO(UUID uuid, String code, String title, String categoryName) {
        this.uuid = uuid;
        this.code = code;
        this.title = title;
        this.categoryName = categoryName;
    }


    public PermissionDTO() {}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getCategoryUUID() {
        return categoryUUID;
    }

    public void setCategoryUUID(UUID categoryUUID) {
        this.categoryUUID = categoryUUID;
    }

    @Override
    public String toString() {
        return "PermissionDTO{" +
                "id=" + uuid +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
