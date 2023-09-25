package com.gaindesat.ddp.serviceinterface;

import com.gaindesat.ddp.dto.MissionDataDTO;
import com.gaindesat.ddp.models.MissionData;
import com.gaindesat.ddp.models.Partner;
import com.gaindesat.ddp.models.Sensor;
import com.gaindesat.ddp.models.SensorDataCollector;

public interface MissionDataServiceInterface {

    MissionData populateMissionData(MissionDataDTO missionDataDTO, MissionData missionData);

    MissionData populateMissionData(MissionDataDTO missionDataDTO, MissionData missionData, Partner partner, Sensor sensor);
}
