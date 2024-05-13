package com.gaindesat.ddp.controllers.admin;

import com.gaindesat.ddp.dto.ParameterDTO;
import com.gaindesat.ddp.dto.incoming.IParameterDTO;
import com.gaindesat.ddp.models.Parameter;
import com.gaindesat.ddp.models.Sensor;
import com.gaindesat.ddp.repository.ParameterRepository;
import com.gaindesat.ddp.repository.SensorRepository;
import com.gaindesat.ddp.service.ParameterService;
import com.gaindesat.ddp.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.net.URI;
import java.util.*;


@RestController
@RequestMapping("api/v1/admin")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class ParameterController {

    @Autowired
    ParameterService parameterService;

    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    ParameterRepository parameterRepository;

    @GetMapping("/parameters")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<ParameterDTO>> allParameters() {
        Iterable<Parameter> allParameters = this.parameterRepository.findAll();
        List<ParameterDTO> parameterDTOList = new ArrayList<>();
        allParameters.forEach(parameter -> {
            ParameterDTO parameterDTO = new ParameterDTO(
                    parameter.getUuid(),
                    parameter.getName(),
                    parameter.getUnite(),
                    parameter.getDescription(),
                    parameter.getSensors()
            );
            parameterDTOList.add(parameterDTO);
        });

        return new ResponseEntity<>(parameterDTOList, HttpStatus.OK);
    }

    @PostMapping("/parameters")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createParameter(@Valid @RequestBody IParameterDTO iParameterDTO) {
        Set<Sensor> sensors = new HashSet<>();
        iParameterDTO.getSensors().forEach(sensorUUID -> {
            Optional<Sensor> sensor = sensorRepository.findById(UUID.fromString(sensorUUID));
            sensor.ifPresent(sensors::add);
        });
        Parameter persistenceParameter = parameterService.populateParameter(iParameterDTO, sensors, new Parameter());
        try {
            parameterRepository.save(persistenceParameter);
            HttpHeaders responseHeaders = new HttpHeaders();
            URI newParameterUri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("{id}")
                    .buildAndExpand(persistenceParameter.getUuid())
                    .toUri();
            responseHeaders.setLocation(newParameterUri);
            return new ResponseEntity<>(persistenceParameter, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>("Error occur! Parameter not added", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping("/parameters/{parameterUUID}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateParameter(@Valid @RequestBody ParameterDTO parameterDTO, @PathVariable UUID parameterUUID) {
        Optional<Parameter> optionalParameter = this.parameterRepository.findById(parameterUUID);
        if(optionalParameter.isPresent()) {
            Parameter persistenceParameter = this.parameterService.populateParameter(parameterDTO, optionalParameter.get());
            this.parameterRepository.save(persistenceParameter);

            return new ResponseEntity<>(persistenceParameter, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/paremeters/{parameterUUID}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteParameter(@PathVariable UUID parameterUUID) {
        try {
            this.parameterRepository.deleteById(parameterUUID);
            return new ResponseEntity<>("{\"message\" :\"Parameter deleted\"}", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("{\"message\" :\"Error Occur parameter not deleted\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
