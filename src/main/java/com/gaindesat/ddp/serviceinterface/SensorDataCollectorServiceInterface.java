package com.gaindesat.ddp.serviceinterface;

import com.gaindesat.ddp.dto.SensorDataCollectorDTO;
import com.gaindesat.ddp.models.Partner;
import com.gaindesat.ddp.models.SensorDataCollector;

public interface SensorDataCollectorServiceInterface {
    SensorDataCollector populateSensorDataCollector(SensorDataCollectorDTO sensorDataCollectorDTO, SensorDataCollector sensorDataCollector, Partner partner);
    SensorDataCollector populateSensorDataCollector(SensorDataCollectorDTO sensorDataCollectorDTO, SensorDataCollector sensorDataCollector);
}
