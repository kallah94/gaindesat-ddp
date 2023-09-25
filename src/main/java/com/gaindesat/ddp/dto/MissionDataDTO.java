package com.gaindesat.ddp.dto;

import java.util.Date;
import java.util.UUID;

public class MissionDataDTO {

    private UUID id;
    private Date date;
    private String unit;
    private float value;
    private UUID partnerId;
    private UUID sensorId;

    public MissionDataDTO(Date date, String unit, float value, UUID partnerId, UUID sensorId) {
        this.date = date;
        this.unit = unit;
        this.value = value;
        this.partnerId = partnerId;
        this.sensorId = sensorId;
    }

    public MissionDataDTO(UUID id, Date date, String unit, float value, UUID partnerId, UUID sensorId) {
        this.id = id;
        this.date = date;
        this.unit = unit;
        this.value = value;
        this.partnerId = partnerId;
        this.sensorId = sensorId;
    }

    public MissionDataDTO() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public UUID getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(UUID partnerId) {
        this.partnerId = partnerId;
    }

    public UUID getSensorId() {
        return sensorId;
    }

    public void setSensorId(UUID sensorId) {
        this.sensorId = sensorId;
    }
}
