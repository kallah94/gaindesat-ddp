package com.gaindesat.ddp.serviceinterface;

import com.gaindesat.ddp.dto.ParameterDTO;
import com.gaindesat.ddp.models.Parameter;

public interface ParameterServiceInterface {
    Parameter populateParameter(ParameterDTO parameterDTO, Parameter parameter);
}
