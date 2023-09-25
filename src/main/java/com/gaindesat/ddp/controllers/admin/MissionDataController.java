package com.gaindesat.ddp.controllers.admin;

import com.gaindesat.ddp.dto.MissionDataDTO;
import com.gaindesat.ddp.models.MissionData;
import com.gaindesat.ddp.models.Sensor;
import com.gaindesat.ddp.repository.MissionDataRepository;
import com.gaindesat.ddp.repository.PartnerRepository;
import com.gaindesat.ddp.repository.SensorRepository;
import com.gaindesat.ddp.service.MissionDataService;
import com.gaindesat.ddp.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
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
    PartnerRepository partnerRepository;

    @Autowired
    SensorRepository sensorRepository;

    @GetMapping("/mission-data")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<MissionDataDTO>> getALlMissionData() {
        Iterable<MissionData> allMissionData = this.missionDataRepository.findAll();
        List<MissionDataDTO> missionDataDTOList = new ArrayList<>();
        allMissionData.forEach(missionData -> {
            MissionDataDTO missionDataDTO = new MissionDataDTO(
                    missionData.getId(),
                    missionData.getDate(),
                    missionData.getUnit(),
                    missionData.getValue(),
                    missionData.getPartner().getId(),
                    missionData.getSensor().getId()
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

}
