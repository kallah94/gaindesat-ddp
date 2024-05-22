package com.gaindesat.ddp.service;

import com.gaindesat.ddp.dto.ParameterDTO;
import com.gaindesat.ddp.models.Parameter;
import com.gaindesat.ddp.repository.SensorRepository;
import com.gaindesat.ddp.serviceinterface.ParameterServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ParameterService implements ParameterServiceInterface {

    @Autowired
    private SensorRepository sensorRepository;
    @Override
    public Parameter populateParameter(ParameterDTO parameterDTO, Parameter persistenceParameter) {
        persistenceParameter.setName(parameterDTO.getName());
        persistenceParameter.setUnite(parameterDTO.getUnite());
        persistenceParameter.setDescription(parameterDTO.getDescription());
        return persistenceParameter;
    }
}
