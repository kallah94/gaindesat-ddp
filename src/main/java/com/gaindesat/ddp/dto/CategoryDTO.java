package com.gaindesat.ddp.dto;

import java.util.Objects;

public class CategoryDTO {
    private String code;
    private String catName;

    public CategoryDTO(String code, String catName) {
        this.code = code;
        this.catName = catName;
    }

    public CategoryDTO() {
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
                '}';
    }
}
