package com.gaindesat.ddp.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class RoleDTO implements Serializable {

    private String code;
    private String title;
    private String categoryUUID;

    public RoleDTO(String code, String title, String categoryUUID) {
        this.code = code;
        this.title = title;
        this.categoryUUID = categoryUUID;
    }

    public RoleDTO() {
    }

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

    public String getCategoryUUID() {
        return categoryUUID;
    }

    public void setCategoryUUID(String categoryUUID) {
        this.categoryUUID = categoryUUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleDTO)) return false;
        RoleDTO roleDTO = (RoleDTO) o;
        return getCode().equals(roleDTO.getCode()) && getTitle().equals(roleDTO.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getTitle());
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                " categoryUUID'" + categoryUUID + '\'' +
                '}';
    }
}
