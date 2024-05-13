package com.gaindesat.ddp.dto;

import com.gaindesat.ddp.models.Parameter;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class SensorDTO {

    private UUID uuid;
    private String code;
    private String name;
    private String type;
    private UUID  stationUuid;
    private Set<Parameter> parameters;

    public SensorDTO(UUID uuid, String code, String name, String type, Set<Parameter> parameters) {
        this.uuid = uuid;
        this.code = code;
        this.name = name;
        this.type = type;
        this.parameters = parameters;
    }

    public SensorDTO(UUID uuid,
                     String code,
                     String name,
                     String type,
                     UUID stationUuid,
                     Set<Parameter> parameters) {
        this.uuid = uuid;
        this.code = code;
        this.name = name;
        this.type = type;
        this.stationUuid = stationUuid;
        this.parameters = parameters;
    }

    public SensorDTO() {}

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(Set<Parameter> parameters) {
        this.parameters = parameters;
    }

    public UUID getStationUuid() {
        return stationUuid;
    }

    public void setStationUuid(UUID stationUuid) {
            this.stationUuid = stationUuid;
    }

    @Override
    public String toString() {
        return "SensorDTO{" +
                "uuid=" + uuid +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", stationUuid=" + stationUuid +
                ", parameters=" + parameters +
                '}';
    }
}
