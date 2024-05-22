package com.gaindesat.ddp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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

    @ElementCollection
    @CollectionTable(name = "sensor_parameters", joinColumns = @JoinColumn(name = "sensor_id"))
    @Column(name = "parameter")
    private Set<String> parameters;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "sensorDataCollector_id")
    private SensorDataCollector sensorDataCollector;

    // Getters and Setters, equals and hashCode methods

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

    public Set<String> getParameters() {
        return parameters;
    }

    public void setParameters(Set<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return Objects.equals(uuid, sensor.uuid) && Objects.equals(code, sensor.code) && Objects.equals(name, sensor.name) && Objects.equals(type, sensor.type) && Objects.equals(sensorDataCollector, sensor.sensorDataCollector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, code, name, type, sensorDataCollector);
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", sensorDataCollector=" + sensorDataCollector +
                '}';
    }
}
