package com.gaindesat.ddp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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

    @Column(name = "parametresmesurees", nullable = false)
    @ElementCollection()
    private List<String> parameters;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "sensorDataCollector_id")
    private SensorDataCollector sensorDataCollector;


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

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }


    public SensorDataCollector getSensorDataCollector() {
        return sensorDataCollector;
    }

    public void setSensorDataCollector(SensorDataCollector sensorDataCollector) {
        this.sensorDataCollector = sensorDataCollector;
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
                Objects.equals(getType(), sensor.getType())
                &&
                Objects.equals(getParameters(), sensor.getParameters());
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, getCode(), getName(), getType(), getParameters());
    }
}
