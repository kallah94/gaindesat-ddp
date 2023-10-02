package com.gaindesat.ddp.controllers.admin;

import com.gaindesat.ddp.dto.SensorDTO;
import com.gaindesat.ddp.models.Sensor;
import com.gaindesat.ddp.models.SensorDataCollector;
import com.gaindesat.ddp.repository.SensorDataCollectorRepository;
import com.gaindesat.ddp.repository.SensorRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/admin")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class SensorController {
    @Autowired
    SensorService sensorService;

    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    SensorDataCollectorRepository sensorDataCollectorRepository;

    @GetMapping("/sensors")
    @Produces({MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Iterable<SensorDTO>> getAllSensors() {
        Iterable<Sensor> allSensors = this.sensorRepository.findAll();
        List<SensorDTO> sensorDTOList = new ArrayList<>();
        allSensors.forEach(sensor -> {
            SensorDTO sensorDTO = new SensorDTO(
                    sensor.getUuid(),
                    sensor.getCode(),
                    sensor.getName(),
                    sensor.getType(),
                    sensor.getSensorDataCollector().getUuid(),
                    sensor.getParameters()
            );
            sensorDTOList.add(sensorDTO);
        });
        return new ResponseEntity<>(sensorDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/categories/{sensorId}")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSensor(@PathVariable UUID sensorId) {
        Optional<Sensor> optionalSensor = this.sensorRepository.findById(sensorId);

        return optionalSensor.<ResponseEntity<Object>>map( sensor ->
                new ResponseEntity<>(sensor, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>("{Sensor not found}", HttpStatus.NOT_FOUND));
    }

    @PostMapping("/sensors")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createSensor(@RequestBody SensorDTO sensorDTO) {
        Optional<SensorDataCollector> sensorDataCollector = sensorDataCollectorRepository.findById(sensorDTO.getSensorDataCollectorUUID());
        if (sensorDataCollector.isPresent()) {
            Sensor persistenceSensor = sensorService.populateSensor(sensorDTO, new Sensor(), sensorDataCollector.get());
            sensorRepository.save(persistenceSensor);

            HttpHeaders responseHeaders = new HttpHeaders();
            URI newSensorUrl = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("{id}")
                    .buildAndExpand(persistenceSensor.getUuid())
                    .toUri();
            responseHeaders.setLocation(newSensorUrl);
            return new ResponseEntity<>(persistenceSensor, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("{not created}", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/sensors/{sensorId}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateSensor(@RequestBody SensorDTO sensorDTO, @PathVariable UUID sensorId) {
        Optional<Sensor> sensorOptional = sensorRepository.findById(sensorId);

        if (sensorOptional.isPresent()) {
            Sensor persistenceSensor = sensorService.populateSensor(sensorDTO, sensorOptional.get());
            sensorRepository.save(persistenceSensor);

            return new ResponseEntity<>(persistenceSensor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not Found !", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/sensors/{sensorId}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteSensor(@PathVariable UUID sensorId) {
        sensorRepository.deleteById(sensorId);
        return new ResponseEntity<>("sensor removed successfully", HttpStatus.OK);
    }
}
