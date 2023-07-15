package com.gaindesat.ddp.dto;

import java.util.UUID;

final public class PermissionDTO {
    private UUID id;
    private String code;
    private String title;

    private String categoryName;

    public PermissionDTO(UUID id, String code, String title, String categoryName) {
        this.id = id;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PermissionDTO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
