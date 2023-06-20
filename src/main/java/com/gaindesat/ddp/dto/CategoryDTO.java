package com.gaindesat.ddp.dto;

import java.util.Objects;
import java.util.UUID;

public class CategoryDTO {
    private UUID uuid;
    private String code;
    private String catName;
    private int userCount;

    public CategoryDTO(UUID uuid, String code, String catName, int userCount) {
        this.uuid = uuid;
        this.code = code;
        this.catName = catName;
        this.userCount = userCount;
    }

    public CategoryDTO() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryDTO)) return false;
        CategoryDTO that = (CategoryDTO) o;
        return getCode().equals(that.getCode()) && getCatName().equals(that.getCatName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getCatName());
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "code='" + code + '\'' +
                ", catName='" + catName + '\'' +
                ", userCount=" + userCount +
                '}';
    }
}
