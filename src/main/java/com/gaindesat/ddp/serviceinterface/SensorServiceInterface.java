package com.gaindesat.ddp.serviceinterface;

import com.gaindesat.ddp.dto.SensorDTO;
import com.gaindesat.ddp.models.Sensor;

public interface SensorServiceInterface {

    Sensor populateSensor(SensorDTO sensorDTO, Sensor sensor);
}
