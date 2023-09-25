package com.gaindesat.ddp.serviceinterface;

import com.gaindesat.ddp.dto.SensorDTO;
import com.gaindesat.ddp.models.Sensor;
import com.gaindesat.ddp.models.SensorDataCollector;

public interface SensorServiceInterface {

    Sensor populateSensor(SensorDTO sensorDTO, Sensor sensor, SensorDataCollector sensorDataCollector);
    Sensor populateSensor(SensorDTO sensorDTO, Sensor sensor);
}
