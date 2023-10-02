package com.gaindesat.ddp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import java.util.Set;

@Entity
@Table(name = "TB_Balise_de_collecte")
public class SensorDataCollector implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid")
    private UUID uuid;

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

    @OneToMany(mappedBy = "sensorDataCollector", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Sensor> sensors;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "partner_id")
    private Partner partner;

    public UUID getUuid() {
        return uuid;
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
        return Float.compare(
                that.getLongitude(),
                getLongitude()) == 0
                &&
                Float.compare(
                        that.getLatitude(),
                        getLatitude()) == 0
                &&
                Float.compare(
                        that.getElevation(),
                        getElevation()) == 0
                && getUuid().equals(that.getUuid())
                && getCode().equals(that.getCode())
                && getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getCode(), getName(), getLongitude(), getLatitude(), getElevation());
    }

    public Set<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(Set<Sensor> sensors) {
        this.sensors = sensors;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }
}
