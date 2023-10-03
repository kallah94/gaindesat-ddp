package com.gaindesat.ddp.controllers.partners;

import com.gaindesat.ddp.dto.MemberDTO;
import com.gaindesat.ddp.models.Partner;
import com.gaindesat.ddp.models.SensorDataCollector;
import com.gaindesat.ddp.models.User;
import com.gaindesat.ddp.repository.PartnerRepository;
import com.gaindesat.ddp.repository.UserRepository;
import com.gaindesat.ddp.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.repository.query.Param;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/resources")
public class PartnershipController {

    @Autowired
    PartnerRepository partnerRepository;
    @Autowired
    UserRepository userRepository;



    @PreAuthorize("(#partnerUUID == authentication.principal.partnerUUID) or hasRole('ADMIN')")
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

    @PreAuthorize("(authentication.principal.partnerUUID == #partnerUUID) or hasRole('ADMIN')")
    @GetMapping("/sensor-data-collectors/{partnerUUID}")
    @Produces({MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getPartnerAllSensorDataCollectors(@Valid @PathVariable  UUID partnerUUID) {
        Optional<Set<SensorDataCollector>> partnerSensorDataCollectors = this.partnerRepository.findById(partnerUUID).map(Partner::getSensorDataCollectors);
        if (partnerSensorDataCollectors.isPresent()) {
            return new ResponseEntity<>(partnerSensorDataCollectors.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("No Sensor Data Collector found!!!", HttpStatus.NOT_FOUND);
    }


}
