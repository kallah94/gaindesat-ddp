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
    private Set<Sensor> sensors;
    public ParameterDTO() {};

    public ParameterDTO(
            UUID uuid,
            String name,
            String unite,
            String description,
            Set<Sensor> sensors) {
        this.uuid = uuid;
        this.name = name;
        this.unite = unite;
        this.description = description;
        this.sensors = sensors;
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

    public Set<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(Set<Sensor> sensors) {
        this.sensors = sensors;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParameterDTO that = (ParameterDTO) o;
        return Objects.equals(uuid, that.uuid);
    }
    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
    @Override
    public String toString() {
        return "ParameterDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", unite='" + unite + '\'' +
                ", sensors=" + sensors +
                '}';
    }
}
