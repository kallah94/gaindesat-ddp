package com.gaindesat.ddp.service;

import com.gaindesat.ddp.dto.SensorDTO;
import com.gaindesat.ddp.models.Sensor;
import com.gaindesat.ddp.models.SensorDataCollector;
import com.gaindesat.ddp.serviceinterface.SensorServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class SensorService implements SensorServiceInterface {
    @Override
    public Sensor populateSensor(SensorDTO sensorDTO, Sensor sensor, SensorDataCollector sensorDataCollector) {
        sensor.setCode(sensorDTO.getCode());
        sensor.setName(sensorDTO.getName());
        sensor.setType(sensorDTO.getType());
        sensor.setParameters(sensorDTO.getParameters());
        sensor.setSensorDataCollector(sensorDataCollector);
        return sensor;
    }

    @Override
    public Sensor populateSensor(SensorDTO sensorDTO, Sensor sensor) {
        sensor.setCode(sensorDTO.getCode());
        sensor.setName(sensorDTO.getName());
        sensor.setType(sensorDTO.getType());
        sensor.setParameters(sensorDTO.getParameters());
        return sensor;
    }
}
