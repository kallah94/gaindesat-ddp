package com.gaindesat.ddp.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TB_Balise_de_collecte")
public class SensorDataCollector implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "longitude", nullable = false)
    private float longitude;

    @Column(name = "latitude", nullable = false)
    private float latitude;

    @Column(name = "elevation", nullable = false)
    private float elevation;

    public UUID getId() {
        return id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SensorDataCollector)) return false;
        SensorDataCollector that = (SensorDataCollector) o;
        return Float.compare(that.getLongitude(), getLongitude()) == 0 && Float.compare(that.getLatitude(), getLatitude()) == 0 && Float.compare(that.getElevation(), getElevation()) == 0 && getId().equals(that.getId()) && getCode().equals(that.getCode()) && getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getName(), getLongitude(), getLatitude(), getElevation());
    }

    @Override
    public String toString() {
        return "SensorDataCollector{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", elevation=" + elevation +
                '}';
    }
}
