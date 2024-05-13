package com.gaindesat.ddp.service;

import com.gaindesat.ddp.dto.MissionDataDTO;
import com.gaindesat.ddp.models.MissionData;
import com.gaindesat.ddp.models.Sensor;
import com.gaindesat.ddp.models.SensorDataCollector;
import com.gaindesat.ddp.repository.MissionDataRepository;
import com.gaindesat.ddp.repository.SensorDataCollectorRepository;
import com.gaindesat.ddp.repository.SensorRepository;
import com.gaindesat.ddp.serviceinterface.MissionDataServiceInterface;
import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForYear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MissionDataService implements MissionDataServiceInterface {

    @Autowired
    MissionDataRepository missionDataRepository;

    @Autowired
    SensorDataCollectorRepository sensorDataCollectorRepository;

    @Autowired
    SensorRepository sensorRepository;

    /**
     * @param missionDataDTO
     * @param missionData
     * @param sensor
     * @return
     */
    @Override
    public MissionData populateMissionData(MissionDataDTO missionDataDTO, MissionData missionData, Sensor sensor) {
        missionData.setDate(missionDataDTO.getDate());
        missionData.setParameter(missionDataDTO.getParameter());
        missionData.setUnit(missionDataDTO.getUnit());
        missionData.setValue(missionDataDTO.getValue());
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

    public Set<MissionData> getSensorMissionDataList(UUID sensorUUID) {
        Optional<Set<MissionData>> missionDataList = missionDataRepository.findAllBySensorUuid(sensorUUID);
        return missionDataList.orElseGet(() -> (Set<MissionData>) new ArrayList<MissionData>());
    }

    public List<MissionDataDTO> getAllSensorDataCollectorMissionData(SensorDataCollector sensorDataCollector) {
        List<MissionDataDTO> missionDataList = new ArrayList<>();
        sensorDataCollector.getSensors().forEach(sensor -> {
            Set<MissionData> missionDataSet = getSensorMissionDataList(sensor.getUuid());
            if (!missionDataSet.isEmpty()) {
                List<MissionDataDTO> missionDataDTOList = missionDataSet
                        .stream()
                        .map(missionData -> new MissionDataDTO(
                                missionData.getUuid(),
                                missionData.getDate(),
                                missionData.getParameter(),
                                missionData.getUnit(),
                                missionData.getValue(),
                                missionData.getSensor().getCode()
                        )).collect(Collectors.toList());
                missionDataList.addAll(missionDataDTOList);
            }
        });
        return missionDataList;
    }

    public Optional<Sensor> missionDataSensor(String dataCollectorCode, String sensorCode) {

        Optional<SensorDataCollector> sensorDataCollector = sensorDataCollectorRepository.findByCode(dataCollectorCode);
        return sensorDataCollector.map(dataCollector -> dataCollector.getSensors().stream().filter(sensor -> sensorCode.equals(sensor.getCode())).findAny()).orElse(null);
    }
}