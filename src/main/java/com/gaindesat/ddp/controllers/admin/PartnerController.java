package com.gaindesat.ddp.controllers.admin;

import com.gaindesat.ddp.dto.PartnerDTO;
import com.gaindesat.ddp.models.Partner;
import com.gaindesat.ddp.repository.PartnerRepository;
import com.gaindesat.ddp.service.PartnerService;
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
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/admin")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class PartnerController {
    PartnerService partnerService;
    @Autowired
    PartnerRepository partnerRepository;

    @GetMapping("/partners")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Partner>> allPartners() {

        Iterable<Partner> allPartners = this.partnerRepository.findAll();
        return new ResponseEntity<>(allPartners, HttpStatus.OK);
    }

    @GetMapping("/partners/{partnerId}")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPartner(@PathVariable UUID partnerId) {
        Optional<Partner> optionalPartner = this.partnerRepository.findById(partnerId);

        return optionalPartner.<ResponseEntity<Object>>map(partner ->
                new ResponseEntity<>(partner, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>("{Partner not found }", HttpStatus.NOT_FOUND));
    }

    @PostMapping("/partners")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Partner> createPartner(@Valid @RequestBody PartnerDTO partnerDTO) {
        Partner persistencePartner = this.partnerService.populatePartner(partnerDTO, new Partner());
        this.partnerRepository.save(persistencePartner);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(persistencePartner.getId())
                .toUri();
        responseHeaders.setLocation(newUserUri);
        return new ResponseEntity<>(persistencePartner, HttpStatus.CREATED);
    }

    @PutMapping("/partners/{partnerId}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updatePartner(@Valid @RequestBody PartnerDTO partnerDTO, @PathVariable UUID partnerId) {
        Optional<Partner> optionalPartner = this.partnerRepository.findById(partnerId);
        if(optionalPartner.isPresent()){
            Partner persistencePartner = this.partnerService.populatePartner(partnerDTO, optionalPartner.get());
            this.partnerRepository.save(persistencePartner);

            return new ResponseEntity<>(persistencePartner, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not found !", HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/partners/{partnerId}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deletePartner(@PathVariable UUID partnerId) {
        this.partnerRepository.deleteById(partnerId);

        return new ResponseEntity<>("partner removed successfully", HttpStatus.OK);
    }
}
