package com.gaindesat.ddp.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_parametres")
public class Parameter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "unite", nullable = false)
    private String unite;

    @ManyToMany(mappedBy = "parameters", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Sensor> sensors = new HashSet<>();
    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public Set<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(Set<Sensor> sensors) {
        this.sensors = sensors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameter parameter = (Parameter) o;
        return Objects.equals(uuid, parameter.uuid) && Objects.equals(name, parameter.name) && Objects.equals(description, parameter.description) && Objects.equals(unite, parameter.unite) && Objects.equals(sensors, parameter.sensors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, description, unite, sensors);
    }
}
