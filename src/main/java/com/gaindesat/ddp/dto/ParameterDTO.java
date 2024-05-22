package com.gaindesat.ddp.dto;

import com.gaindesat.ddp.models.Sensor;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class ParameterDTO {
    private UUID uuid;
    private String name;
    private String description;
    private String unite;
    public ParameterDTO() {};

    public ParameterDTO(
            UUID uuid,
            String name,
            String unite,
            String description) {
        this.uuid = uuid;
        this.name = name;
        this.unite = unite;
        this.description = description;
    }
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    @Override
    public String toString() {
        return "ParameterDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", unite='" + unite + '\'' +
                '}';
    }
}
