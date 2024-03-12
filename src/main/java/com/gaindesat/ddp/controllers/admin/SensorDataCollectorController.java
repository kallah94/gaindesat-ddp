package com.gaindesat.ddp.controllers.admin;

import com.gaindesat.ddp.dto.SensorDataCollectorDTO;
import com.gaindesat.ddp.models.Partner;
import com.gaindesat.ddp.models.SensorDataCollector;
import com.gaindesat.ddp.repository.PartnerRepository;
import com.gaindesat.ddp.repository.SensorDataCollectorRepository;
import com.gaindesat.ddp.service.SensorDataCollectorService;
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
public class SensorDataCollectorController {
    @Autowired
    SensorDataCollectorService sensorDataCollectorService;
    @Autowired
    SensorDataCollectorRepository sensorDataCollectorRepository;
    @Autowired
    PartnerRepository partnerRepository;
    @GetMapping("/sensor-data-collectors")
    @Produces({MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Iterable<SensorDataCollectorDTO>> getAllSensorDataCollectors() {
        Iterable<SensorDataCollector> allSensorDataCollectors = this.sensorDataCollectorRepository.findAll();
        List<SensorDataCollectorDTO> sensorDataCollectorDTOList = new ArrayList<>();
        allSensorDataCollectors.forEach(sensorDataCollector -> {
            SensorDataCollectorDTO sensorDataCollectorDTO = new SensorDataCollectorDTO(
                    sensorDataCollector.getUuid(),
                    sensorDataCollector.getCode(),
                    sensorDataCollector.getName(),
                    sensorDataCollector.getLongitude(),
                    sensorDataCollector.getLatitude(),
                    sensorDataCollector.getElevation(),
                    sensorDataCollector.getPartner().getUuid(),
                    sensorDataCollector.getPartner().getCode(),
                    sensorDataCollector.getSensors()
            );
            sensorDataCollectorDTOList.add(sensorDataCollectorDTO);
        });
        return new ResponseEntity<>(sensorDataCollectorDTOList, HttpStatus.OK);
    }
    @GetMapping(value = "/sensor-data-collectors/{sensorDataCollectorId}")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSensorDataCollector(@PathVariable UUID sensorDataCollectorId) {
        Optional<SensorDataCollector> optionalSensorDataCollector = this.sensorDataCollectorRepository.findById(sensorDataCollectorId);

        return optionalSensorDataCollector.<ResponseEntity<Object>>map( sensorDataCollector ->
            new ResponseEntity<>(sensorDataCollector, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>("{SensorDataCollector not Found}", HttpStatus.NOT_FOUND));
    }
    @PostMapping("/sensor-data-collectors")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createSensorDataCollector(@RequestBody SensorDataCollectorDTO sensorDataCollectorDTO) {
        Optional<Partner> optionalPartner = partnerRepository.findById(sensorDataCollectorDTO.getPartnerUUID());
        if(optionalPartner.isPresent()) {
            SensorDataCollector persistenceSensorDataCollector = sensorDataCollectorService.populateSensorDataCollector(sensorDataCollectorDTO, new SensorDataCollector(), optionalPartner.get());
            sensorDataCollectorRepository.save(persistenceSensorDataCollector);
            SensorDataCollectorDTO responseSensorDataCollector = new SensorDataCollectorDTO(
                    persistenceSensorDataCollector.getUuid(),
                    persistenceSensorDataCollector.getCode(),
                    persistenceSensorDataCollector.getName(),
                    persistenceSensorDataCollector.getLongitude(),
                    persistenceSensorDataCollector.getLatitude(),
                    persistenceSensorDataCollector.getElevation(),
                    persistenceSensorDataCollector.getPartner().getUuid()
            );
            HttpHeaders responseHeaders = new HttpHeaders();
            URI newSensorDataCollectorUri = ServletUriComponentsBuilder
                    .fromCurrentRequestUri()
                    .path("{id}")
                    .buildAndExpand(persistenceSensorDataCollector.getUuid())
                    .toUri();
            responseHeaders.setLocation(newSensorDataCollectorUri);
            return new ResponseEntity<>(responseSensorDataCollector, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("{Not created}", HttpStatus.NOT_FOUND);
    }
    @PutMapping("/sensor-data-collectors/{sensorDataCollectorId}")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateSensorDataCollector(@Valid @RequestBody SensorDataCollectorDTO sensorDataCollectorDTO, @PathVariable UUID sensorDataCollectorUUID) {
        Optional<SensorDataCollector> optionalSensorDataCollector = sensorDataCollectorRepository.findById(sensorDataCollectorUUID);
        if(optionalSensorDataCollector.isPresent()) {
            SensorDataCollector sensorDataCollector = sensorDataCollectorService.populateSensorDataCollector(sensorDataCollectorDTO, optionalSensorDataCollector.get());
            sensorDataCollectorRepository.save(sensorDataCollector);
            return new ResponseEntity<>(sensorDataCollector, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found!!!", HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping("sensor-data-collectors/{sensorDataCollectorId}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteSensorDataCollector(@PathVariable UUID sensorDataCollectorId) {
        Optional<SensorDataCollector> deletedSensorDataCollector = sensorDataCollectorRepository.findById(sensorDataCollectorId);
        if (deletedSensorDataCollector.isPresent()) {
            try {
                sensorDataCollectorRepository.deleteById(sensorDataCollectorId);
                return new ResponseEntity<>(deletedSensorDataCollector, HttpStatus.OK);
            } catch (Exception exception) {
                return new ResponseEntity<>("SensorDataCollector can't be delete !!!", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("SensorDataCollector not found !!!", HttpStatus.NOT_FOUND);
    }
}