package com.gaindesat.ddp.controllers.admin;

import com.gaindesat.ddp.dto.MissionDataDTO;
import com.gaindesat.ddp.models.MissionData;
import com.gaindesat.ddp.models.Sensor;
import com.gaindesat.ddp.repository.MissionDataRepository;
import com.gaindesat.ddp.repository.SensorRepository;
import com.gaindesat.ddp.service.MissionDataService;
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
public class MissionDataController {
    @Autowired
    MissionDataService missionDataService;
    @Autowired
    MissionDataRepository missionDataRepository;
    @Autowired
    SensorRepository sensorRepository;
    @GetMapping("/mission-data")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<MissionDataDTO>> getALlMissionData() {
        Iterable<MissionData> allMissionData = this.missionDataRepository.findAll();
        List<MissionDataDTO> missionDataDTOList = new ArrayList<>();
        allMissionData.forEach(missionData -> {
            MissionDataDTO missionDataDTO = new MissionDataDTO(
                    missionData.getUuid(),
                    missionData.getDate(),
                    missionData.getParameter(),
                    missionData.getUnit(),
                    missionData.getValue(),
                    missionData.getSensor().getUuid()
            );
            missionDataDTOList.add(missionDataDTO);
        });
        return new ResponseEntity<>(missionDataDTOList, HttpStatus.OK);
    }

    @GetMapping("/mission-data/{missionDataId}")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMissionData(@PathVariable UUID missionDataId) {
        Optional<MissionData> optionalMissionData = this.missionDataRepository.findById(missionDataId);

        return optionalMissionData.<ResponseEntity<Object>>map( missionData ->
                new ResponseEntity<>(missionData, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>("{Mission Data not found}", HttpStatus.NOT_FOUND));
    }

    @PostMapping("/mission-data")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createMissionData(@RequestBody MissionDataDTO missionDataDTO) {
        Optional<Sensor> sensor = sensorRepository.findById(missionDataDTO.getSensorUuid());
        if(sensor.isPresent()) {
                MissionData missionData = missionDataService.populateMissionData(missionDataDTO, new MissionData() ,sensor.get());
                missionDataRepository.save(missionData);
                MissionDataDTO responseMissionDTO = new MissionDataDTO(
                        missionData.getUuid(),
                        missionData.getDate(),
                        missionData.getParameter(),
                        missionData.getUnit(),
                        missionData.getValue(),
                        missionData.getSensor().getUuid()
                );
                HttpHeaders responseHeaders = new HttpHeaders();
                URI newSensorUrl = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("{id}")
                        .buildAndExpand(missionData.getUuid())
                        .toUri();
                responseHeaders.setLocation(newSensorUrl);
                return new ResponseEntity<>(responseMissionDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Sensor not found !!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/mission-data/{missionDataId}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteMissionData(@PathVariable UUID missionDataId) {
        Optional<MissionData> deletedMissionData = missionDataRepository.findById(missionDataId);
        if (deletedMissionData.isPresent()) {
            missionDataRepository.deleteById(missionDataId);
            return new ResponseEntity<>(deletedMissionData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Mission data not found !!!", HttpStatus.NOT_FOUND);
    }
}
