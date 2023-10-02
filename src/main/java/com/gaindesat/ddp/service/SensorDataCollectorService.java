package com.gaindesat.ddp.service;

import com.gaindesat.ddp.dto.SensorDataCollectorDTO;
import com.gaindesat.ddp.models.Partner;
import com.gaindesat.ddp.models.SensorDataCollector;
import com.gaindesat.ddp.serviceinterface.SensorDataCollectorServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class SensorDataCollectorService implements SensorDataCollectorServiceInterface {

    /**
     * @param sensorDataCollectorDTO 
     * @param sensorDataCollector
     * @param partner
     * @return sensorDataCollector
     */
    @Override
    public SensorDataCollector populateSensorDataCollector(SensorDataCollectorDTO sensorDataCollectorDTO, SensorDataCollector sensorDataCollector, Partner partner) {
        sensorDataCollector.setCode(sensorDataCollectorDTO.getCode());
        sensorDataCollector.setName(sensorDataCollectorDTO.getName());
        sensorDataCollector.setLongitude(sensorDataCollectorDTO.getLongitude());
        sensorDataCollector.setLatitude(sensorDataCollectorDTO.getLatitude());
        sensorDataCollector.setElevation(sensorDataCollectorDTO.getElevation());
        sensorDataCollector.setPartner(partner);
        return sensorDataCollector;
    }

    /**
     * @param sensorDataCollectorDTO 
     * @param sensorDataCollector
     * @return sensorDataCollector
     */
    @Override
    public SensorDataCollector populateSensorDataCollector(SensorDataCollectorDTO sensorDataCollectorDTO, SensorDataCollector sensorDataCollector) {
        return null;
    }
}
