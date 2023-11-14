package com.gaindesat.ddp.dto;


public class MissionDataDTOFromDT {

    private String id_station;
    private String sensor_id;
    private long parameter_value;
    private String parameter_type;
    private String measure_timestamp;

    public MissionDataDTOFromDT(String id_station, String sensor_id, long parameter_value, String parameter_type, String measure_timestamp) {
        this.id_station = id_station;
        this.sensor_id = sensor_id;
        this.parameter_value = parameter_value;
        this.parameter_type = parameter_type;
        this.measure_timestamp = measure_timestamp;
    }

    public String getParameter_type() {
        return parameter_type;
    }

    public void setParameter_type(String parameter_type) {
        this.parameter_type = parameter_type;
    }

    public String getId_station() {
        return id_station;
    }

    public void setId_station(String id_station) {
        this.id_station = id_station;
    }

    public String getSensor_id() {
        return sensor_id;
    }

    public void setSensor_id(String sensor_id) {
        this.sensor_id = sensor_id;
    }

    public long getParameter_value() {
        return parameter_value;
    }

    public void setParameter_value(long parameter_value) {
        this.parameter_value = parameter_value;
    }

    public String getMeasure_timestamp() {
        return measure_timestamp;
    }

    public void setMeasure_timestamp(String measure_timestamp) {
        this.measure_timestamp = measure_timestamp;
    }
}
