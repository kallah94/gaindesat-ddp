package com.gaindesat.ddp.service;

import com.gaindesat.ddp.dto.MissionDataDTO;
import com.gaindesat.ddp.models.MissionData;
import com.gaindesat.ddp.models.Partner;
import com.gaindesat.ddp.models.Sensor;
import com.gaindesat.ddp.serviceinterface.MissionDataServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class MissionDataService implements MissionDataServiceInterface {
    /**
     * @param missionDataDTO 
     * @param missionData
     * @param partner
     * @param sensor
     * @return
     */
    @Override
    public MissionData populateMissionData(MissionDataDTO missionDataDTO, MissionData missionData, Partner partner, Sensor sensor) {
        missionData.setDate(missionDataDTO.getDate());
        missionData.setParameter(missionDataDTO.getParameter());
        missionData.setUnit(missionDataDTO.getUnit());
        missionData.setValue(missionDataDTO.getValue());
        missionData.setPartner(partner);
        missionData.setSensor(sensor);
        return missionData;
    }

    /**
     * @param missionDataDTO 
     * @param missionData
     * @return
     */
    @Override
    public MissionData populateMissionData(MissionDataDTO missionDataDTO, MissionData missionData) {
        missionData.setDate(missionDataDTO.getDate());
        missionData.setParameter(missionDataDTO.getParameter());
        missionData.setUnit(missionDataDTO.getUnit());
        missionData.setValue(missionDataDTO.getValue());
        return missionData;
    }
}
