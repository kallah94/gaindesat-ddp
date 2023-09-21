package com.gaindesat.ddp.dto;

import java.util.List;
import java.util.UUID;

public class SensorDTO {

    private UUID uuid;
    private String code;
    private String name;
    private String type;
    private List<String> parameters;

    public SensorDTO(UUID uuid, String code, String name, String type, List<String> parameters) {
        this.uuid = uuid;
        this.code = code;
        this.name = name;
        this.type = type;
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

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

}
