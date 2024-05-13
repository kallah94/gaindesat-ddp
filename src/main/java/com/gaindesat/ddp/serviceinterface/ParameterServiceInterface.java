package com.gaindesat.ddp.serviceinterface;

import com.gaindesat.ddp.dto.ParameterDTO;
import com.gaindesat.ddp.dto.incoming.IParameterDTO;
import com.gaindesat.ddp.models.Parameter;
import com.gaindesat.ddp.models.Sensor;
import com.gaindesat.ddp.service.ParameterService;

import java.util.Set;

public interface ParameterServiceInterface {
    Parameter populateParameter(ParameterDTO parameterDTO, Parameter parameter);

    Parameter populateParameter(IParameterDTO iParameterDTO, Set<Sensor> sensors, Parameter parameter);
}
