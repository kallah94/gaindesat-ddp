package com.gaindesat.ddp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "TB_capteur")
public class Sensor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "sensorDataCollector_id")
    private SensorDataCollector sensorDataCollector;

    @ManyToMany
    @JoinTable(
            name = "TB_sensor_parameters",
            joinColumns = @JoinColumn(name = "sensor_uuid"),
            inverseJoinColumns = @JoinColumn(name = "parameter_uuid")
    )
    private Set<Parameter> parameters = new HashSet<>();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public SensorDataCollector getSensorDataCollector() {
        return sensorDataCollector;
    }

    public void setSensorDataCollector(SensorDataCollector sensorDataCollector) {
        this.sensorDataCollector = sensorDataCollector;
    }

    public Set<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(Set<Parameter> parameters) {
        this.parameters = parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sensor)) return false;
        Sensor sensor = (Sensor) o;
        return Objects.equals(uuid, sensor.uuid)
                &&
                Objects.equals(getCode(), sensor.getCode())
                &&
                Objects.equals(getName(), sensor.getName())
                &&
                Objects.equals(getType(), sensor.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, getCode(), getName(), getType());
    }
}
