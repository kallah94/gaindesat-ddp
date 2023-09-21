package com.gaindesat.ddp.dto;

import com.gaindesat.ddp.models.SensorDataCollector;

import java.util.UUID;

public class SensorDataCollectorDTO {

    private UUID id;
    private String code;
    private String name;
    private float longitude;
    private float latitude;
    private float elevation;

    public SensorDataCollectorDTO(UUID id,
                                  String code,
                                  String name,
                                  float longitude,
                                  float latitude,
                                  float elevation) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.elevation = elevation;
    }

    public SensorDataCollectorDTO() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getElevation() {
        return elevation;
    }

    public void setElevation(float elevation) {
        this.elevation = elevation;
    }

    @Override
    public String toString() {
        return "SensorDataCollectorDTO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", elevation=" + elevation +
                '}';
    }
}
