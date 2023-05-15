package com.gaindesat.ddp.dto;

import java.util.Objects;
import java.util.UUID;

public class PartnerDTO {

    private UUID uuid;
    private String code;
    private String name;

    private int userCount;

    public PartnerDTO(UUID uuid, String code, String name, int userCount) {
        this.uuid = uuid;
        this.code = code;
        this.name = name;
        this.userCount = userCount;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public PartnerDTO() {
    }

    public String getCode() {
        return code;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
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
