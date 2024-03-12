package com.gaindesat.ddp.dto;

import java.util.UUID;

public class MissionDataDTO {

    private UUID uuid;
    private String date;
    private String parameter;
    private String unit;
    private float value;
    private UUID sensorUuid;
    private String sensorCode;

    public MissionDataDTO(String date, String parameter, String unit, float value, UUID sensorUuid) {
        this.date = date;
        this.parameter = parameter;
        this.unit = unit;
        this.value = value;
        this.sensorUuid = sensorUuid;
    }

    public MissionDataDTO(UUID uuid, String date, String parameter, String unit, float value, String sensorCode) {
        this.uuid = uuid;
        this.date = date;
        this.parameter = parameter;
        this.unit = unit;
        this.value = value;
        this.sensorCode = sensorCode;
    }

    public MissionDataDTO(UUID uuid, String date, String parameter, String unit, float value) {
        this.uuid = uuid;
        this.date = date;
        this.parameter = parameter;
        this.unit = unit;
        this.value = value;
    }

    public MissionDataDTO() {}

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public UUID getSensorUuid() {
        return sensorUuid;
    }

    public void setSensorUuid(UUID sensorUuid) {
        this.sensorUuid = sensorUuid;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getSensorCode() {
        return sensorCode;
    }

    public void setSensorCode(String sensorCode) {
        this.sensorCode = sensorCode;
    }
}
