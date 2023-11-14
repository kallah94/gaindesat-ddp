package com.gaindesat.ddp.controllers.partners;

import com.gaindesat.ddp.dto.MemberDTO;
import com.gaindesat.ddp.dto.MissionDataDTO;
import com.gaindesat.ddp.models.*;
import com.gaindesat.ddp.repository.MissionDataRepository;
import com.gaindesat.ddp.repository.PartnerRepository;
import com.gaindesat.ddp.repository.SensorDataCollectorRepository;
import com.gaindesat.ddp.repository.UserRepository;
import com.gaindesat.ddp.service.MissionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import java.util.*;

@RestController
@PreAuthorize("(authentication.principal.partnerUUID == #partnerUUID) or hasRole('ADMIN')")
@RequestMapping("api/v1/resources")
public class PartnershipController {

    @Autowired
    PartnerRepository partnerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MissionDataRepository missionDataRepository;
    @Autowired
    MissionDataService missionDataService;
    @Autowired
    SensorDataCollectorRepository sensorDataCollectorRepository;
    /**
     * @param partnerUUID of partner
     * @return the list of all users which are members of the given partnerUUID
     */
    @GetMapping("/members/{partnerUUID}")
    @Produces({MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getMembers( @Valid @PathVariable  UUID partnerUUID) {
        Optional<Set<User>> optionalUsers = partnerRepository.findById(partnerUUID).map(Partner::getUsers);

        return optionalUsers.<ResponseEntity<Object>>map(users ->
                new ResponseEntity<>(users.stream().map(user -> new MemberDTO(
                        user.getUuid(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getFullName(),
                        user.isStatus())), HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>("Error !!!", HttpStatus.NOT_FOUND));
    }

    /**
     * @param partnerUUID of partner
     * @return the list of sensorDataCollectors of the corresponding partner for the given partnerUUID
     */
    @GetMapping("/sensor-data-collectors/{partnerUUID}")
    @Produces({MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getPartnerAllSensorDataCollectors(@Valid @PathVariable  UUID partnerUUID) {
        Optional<Set<SensorDataCollector>> partnerSensorDataCollectors = this.partnerRepository.findById(partnerUUID).map(Partner::getSensorDataCollectors);
        if (partnerSensorDataCollectors.isPresent()) {
            return new ResponseEntity<>(partnerSensorDataCollectors.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("No Sensor Data Collector found!!!", HttpStatus.NOT_FOUND);
    }

    /**
     * @param partnerUUID of partner
     * @param sensorUUID of a specific sensor
     * @return the list of missionData for the corresponding partner and the corresponding sensor
     */
    @GetMapping("/mission-data/sensor-data/{partnerUUID}/{sensorUUID}")
    @Produces({MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getPartnerSensorMissionData(@Valid @PathVariable UUID partnerUUID, @Valid @PathVariable UUID sensorUUID) {
        Optional<Set<MissionData>> optionalMissionData = missionDataRepository.findAllBySensorUuid(sensorUUID);
        return optionalMissionData.<ResponseEntity<Object>>map(missionDataSet ->
                new ResponseEntity<>(missionDataSet.stream().map(missionData -> new MissionDataDTO(
                        missionData.getDate(),
                        missionData.getParameter(),
                        missionData.getUnit(),
                        missionData.getValue(),
                        missionData.getUuid()
                                )), HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>("Error !!!", HttpStatus.NOT_FOUND));
    }

    /**
     * @param partnerUUID of partner
     * @param sensorDataCollectorUUID of a specific sensorDataCollector
     * @return the list of all missionData for all sensors linked with the same given sensorDataCollector
     */
    @GetMapping("/mission-data/collector-data/{partnerUUID}/{sensorDataCollectorUUID}")
    @Produces({MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getPartnerSensorDataCollectorsMissionData(@Valid @PathVariable UUID partnerUUID, @Valid @PathVariable UUID sensorDataCollectorUUID) {
        List<MissionDataDTO> missionDataList = new ArrayList<>();
        Optional<SensorDataCollector> sensorDataCollector = sensorDataCollectorRepository.findById(sensorDataCollectorUUID);
        if(sensorDataCollector.isPresent()) {
            missionDataList.addAll(missionDataService.getAllSensorDataCollectorMissionData(sensorDataCollector.get()));
            return new ResponseEntity<>(missionDataList, HttpStatus.OK);
        }
        return new ResponseEntity<>("error occur", HttpStatus.NOT_FOUND);
    }
}