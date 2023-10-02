package com.gaindesat.ddp.dto;


import com.gaindesat.ddp.models.Sensor;

import java.util.Set;
import java.util.UUID;

public class SensorDataCollectorDTO {

    private UUID uuid;
    private String code;
    private String name;
    private float longitude;
    private float latitude;
    private float elevation;
    private UUID partnerUUID;

    private String partnerCode;

    private Set<Sensor> sensors;

    public SensorDataCollectorDTO(UUID uuid,
                                  String code,
                                  String name,
                                  float longitude,
                                  float latitude,
                                  float elevation,
                                  UUID partnerUUID) {
        this.uuid = uuid;
        this.code = code;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.elevation = elevation;
        this.partnerUUID = partnerUUID;
    }

    public SensorDataCollectorDTO(UUID uuid,
                                  String code,
                                  String name,
                                  float longitude,
                                  float latitude,
                                  float elevation,
                                  UUID partnerUUID,
                                  String partnerCode,
                                  Set<Sensor> sensors) {
        this.uuid = uuid;
        this.code = code;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.elevation = elevation;
        this.partnerUUID = partnerUUID;
        this.partnerCode = partnerCode;
        this.sensors = sensors;
    }

    public SensorDataCollectorDTO(UUID uuid,
                                  String code,
                                  String name,
                                  float longitude,
                                  float latitude,
                                  float elevation,
                                  String partnerCode) {
        this.uuid = uuid;
        this.code = code;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.elevation = elevation;
        this.partnerCode = partnerCode;
    }
    public SensorDataCollectorDTO() {}

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

    public UUID getPartnerUUID() {
        return partnerUUID;
    }

    public void setPartnerUUID(UUID partnerUUID) {
        this.partnerUUID = partnerUUID;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public Set<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(Set<Sensor> sensors) {
        this.sensors = sensors;
    }

    @Override
    public String toString() {
        return "SensorDataCollectorDTO{" +
                "uuid=" + uuid +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", elevation=" + elevation +
                ", partnerUUID=" + partnerUUID +
                ", partnerCode='" + partnerCode + '\'' +
                ", sensors=" + sensors +
                '}';
    }
}
