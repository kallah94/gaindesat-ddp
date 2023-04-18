package com.gaindesat.ddp.dto;

import java.util.Objects;

public class PartnerDTO {

    private String code;
    private String name;

    public PartnerDTO(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public PartnerDTO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PartnerDTO)) return false;
        PartnerDTO that = (PartnerDTO) o;
        return getCode().equals(that.getCode()) && getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getName());
    }

    @Override
    public String toString() {
        return "PartnerDTO{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
