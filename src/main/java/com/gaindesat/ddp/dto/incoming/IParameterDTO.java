package com.gaindesat.ddp.dto.incoming;

import java.util.List;

public class IParameterDTO {

    private String name;
    private  String description;
    private String unite;
    private List<String> sensors;

    public IParameterDTO() {};

    public IParameterDTO(
            String name,
            String description,
            String unite,
            List<String> sensors
    ) {
        this.name = name;
        this.description = description;
        this.unite = unite;
        this.sensors = sensors;
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

    public List<String> getSensors() {
        return sensors;
    }

    public void setSensors(List<String> sensors) {
        this.sensors = sensors;
    }

    @Override
    public String toString() {
        return "IParameterDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", unite='" + unite + '\'' +
                ", sensors=" + sensors +
                '}';
    }
}
