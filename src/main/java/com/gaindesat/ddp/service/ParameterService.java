package com.gaindesat.ddp.service;

import com.gaindesat.ddp.dto.ParameterDTO;
import com.gaindesat.ddp.dto.incoming.IParameterDTO;
import com.gaindesat.ddp.models.Parameter;
import com.gaindesat.ddp.models.Sensor;
import com.gaindesat.ddp.serviceinterface.ParameterServiceInterface;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ParameterService implements ParameterServiceInterface {
    @Override
    public Parameter populateParameter(ParameterDTO parameterDTO, Parameter persistenceParameter) {
        persistenceParameter.setName(parameterDTO.getName());
        persistenceParameter.setUnite(parameterDTO.getUnite());
        persistenceParameter.setDescription(parameterDTO.getDescription());
        persistenceParameter.setSensors(parameterDTO.getSensors());
        return persistenceParameter;
    }

    @Override
    public Parameter populateParameter(IParameterDTO iParameterDTO, Set<Sensor> sensors, Parameter persistenceParameter) {
        persistenceParameter.setName(iParameterDTO.getName());
        persistenceParameter.setDescription(iParameterDTO.getDescription());
        persistenceParameter.setUnite(iParameterDTO.getUnite());
        persistenceParameter.setSensors(sensors);
        return persistenceParameter;
    }
}
