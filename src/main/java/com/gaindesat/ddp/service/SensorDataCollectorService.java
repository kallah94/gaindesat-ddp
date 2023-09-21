package com.gaindesat.ddp.service;

import com.gaindesat.ddp.dto.SensorDataCollectorDTO;
import com.gaindesat.ddp.models.SensorDataCollector;
import com.gaindesat.ddp.serviceinterface.SensorDataCollectorServiceInterface;

public class SensorDataCollectorService implements SensorDataCollectorServiceInterface {
    /**
     * @param sensorDataCollectorDTO 
     * @param sensorDataCollector
     * @return
     */
    @Override
    public SensorDataCollector populateSensorDataCollector(SensorDataCollectorDTO sensorDataCollectorDTO, SensorDataCollector sensorDataCollector) {
        sensorDataCollector.setCode(sensorDataCollectorDTO.getCode());
        sensorDataCollector.setName(sensorDataCollectorDTO.getName());
        sensorDataCollector.setLongitude(sensorDataCollectorDTO.getLongitude());
        sensorDataCollector.setLatitude(sensorDataCollectorDTO.getLatitude());
        sensorDataCollector.setElevation(sensorDataCollectorDTO.getElevation());
        return sensorDataCollector;
    }
}
