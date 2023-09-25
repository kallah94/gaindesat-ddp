package com.gaindesat.ddp.service;

import com.gaindesat.ddp.dto.MissionDataDTO;
import com.gaindesat.ddp.models.MissionData;
import com.gaindesat.ddp.models.Partner;
import com.gaindesat.ddp.models.SensorDataCollector;
import com.gaindesat.ddp.serviceinterface.MissionDataServiceInterface;

public class MissionDataService implements MissionDataServiceInterface {
    /**
     * @param missionDataDTO 
     * @param missionData
     * @return
     */
    @Override
    public MissionData populateMissionData(MissionDataDTO missionDataDTO, MissionData missionData) {
        return null;
    }

    /**
     * @param missionDataDTO 
     * @param missionData
     * @param partner
     * @param sensorDataCollector
     * @return
     */
    @Override
    public MissionData populateMissionData(MissionDataDTO missionDataDTO, MissionData missionData, Partner partner, SensorDataCollector sensorDataCollector) {
        return null;
    }
}
