package com.gaindesat.ddp.controllers.partners;

import com.gaindesat.ddp.dto.MemberDTO;
import com.gaindesat.ddp.models.MissionData;
import com.gaindesat.ddp.models.Partner;
import com.gaindesat.ddp.models.SensorDataCollector;
import com.gaindesat.ddp.models.User;
import com.gaindesat.ddp.repository.PartnerRepository;
import com.gaindesat.ddp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/resources")
public class PartnershipController {

    @Autowired
    PartnerRepository partnerRepository;
    @Autowired
    UserRepository userRepository;

    @PreAuthorize("authentication.principal.partnerId == #partnerId")
    @GetMapping("/members/{partnerId}")
    @Produces({MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getMembers(@Valid @PathVariable UUID partnerId) {
        Optional<Set<User>> optionalUsers = partnerRepository.findById(partnerId).map(Partner::getUsers);

        return optionalUsers.<ResponseEntity<Object>>map(users ->
                new ResponseEntity<>(users.stream().map(user -> new MemberDTO(
                        user.getUuid(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getFullName(),
                        user.isStatus())), HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>("Error !!!", HttpStatus.NOT_FOUND));
    }


    @PreAuthorize("(authentication.principal.partnerId == #partnerId) or hasRole('ADMIN')")
    @GetMapping("/sensor-data-collectors/{partnerId}")
    @Produces({MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getPartnerAllSensorDataCollectors(@Valid @PathVariable  UUID partnerId) {
        Optional<Set<SensorDataCollector>> partnerSensorDataCollectors = this.partnerRepository.findById(partnerId).map(Partner::getSensorDataCollectors);
        if (partnerSensorDataCollectors.isPresent()) {
            return new ResponseEntity<>(partnerSensorDataCollectors.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("No Sensor Data Collector found!!!", HttpStatus.NOT_FOUND);
    }


}
