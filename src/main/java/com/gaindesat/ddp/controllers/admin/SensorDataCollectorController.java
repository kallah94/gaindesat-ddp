package com.gaindesat.ddp.controllers.admin;

import com.gaindesat.ddp.dto.SensorDataCollectorDTO;
import com.gaindesat.ddp.models.SensorDataCollector;
import com.gaindesat.ddp.repository.SensorDataCollectorRepository;
import com.gaindesat.ddp.service.SensorDataCollectorService;
import com.gaindesat.ddp.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("api/vi/admin")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class SensorDataCollectorController {

    @Autowired
    SensorDataCollectorService sensorDataCollectorService;

    @Autowired
    SensorDataCollectorRepository sensorDataCollectorRepository;

    @GetMapping("/sensorDataCollectors")
    @Produces({MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Iterable<SensorDataCollectorDTO>> getAllSensorDataCollectors() {
        Iterable<SensorDataCollector> allSensorDataCollectors = this.sensorDataCollectorRepository.findAll();
        List<SensorDataCollectorDTO> sensorDataCollectorDTOList = new ArrayList<>();
        allSensorDataCollectors.forEach(sensorDataCollector -> {
            SensorDataCollectorDTO sensorDataCollectorDTO = new SensorDataCollectorDTO(
                    sensorDataCollector.getId(),
                    sensorDataCollector.getCode(),
                    sensorDataCollector.getName(),
                    sensorDataCollector.getLongitude(),
                    sensorDataCollector.getLatitude(),
                    sensorDataCollector.getElevation()
            );
            sensorDataCollectorDTOList.add(sensorDataCollectorDTO);
        });

        return new ResponseEntity<>(sensorDataCollectorDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/sensorDataCollectors/{sensorDataCollectorId}")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSensorDataCollector(@PathVariable UUID sensorDataCollectorId) {
        Optional<SensorDataCollector> optionalSensorDataCollector = this.sensorDataCollectorRepository.findById(sensorDataCollectorId);

        return optionalSensorDataCollector.<ResponseEntity<Object>>map( sensorDataCollector ->
            new ResponseEntity<>(sensorDataCollector, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>("{SensorDataCollector not Found}", HttpStatus.NOT_FOUND));
    }

    @PostMapping("/sensorDataCollectors")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SensorDataCollector> createSensorDataCollector(@RequestBody SensorDataCollectorDTO sensorDataCollectorDTO) {
        SensorDataCollector persistenceSensorDataCollector = sensorDataCollectorService.populateSensorDataCollector(sensorDataCollectorDTO, new SensorDataCollector());
        sensorDataCollectorRepository.save(persistenceSensorDataCollector);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newSensorDataCollectorUri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("{id}")
                .buildAndExpand(persistenceSensorDataCollector.getId())
                .toUri();
        responseHeaders.setLocation(newSensorDataCollectorUri);
        return new ResponseEntity<>(persistenceSensorDataCollector, HttpStatus.CREATED);
    }
}
