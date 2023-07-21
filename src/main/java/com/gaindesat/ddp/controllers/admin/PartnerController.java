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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/admin")
public class PartnerController {
    @Autowired
    PartnerService partnerService;
    @Autowired
    PartnerRepository partnerRepository;

    @GetMapping("/partners")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<PartnerDTO>> allPartners() {

        Iterable<Partner> allPartners = this.partnerRepository.findAll();
        List<PartnerDTO> partnerDTOList = new ArrayList<>();
        allPartners.forEach(partner -> {
            PartnerDTO partnerDTO = new PartnerDTO(
                    partner.getId(),
                    partner.getCode(),
                    partner.getPartName(),
                    partner.getUsers().size()
            );
            partnerDTOList.add(partnerDTO);
        });

        return new ResponseEntity<>(partnerDTOList, HttpStatus.OK);
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
    public ResponseEntity<Object> createPartner(@Valid @RequestBody PartnerDTO partnerDTO) {
        Partner persistencePartner = partnerService.populatePartner(partnerDTO, new Partner());

        try {
            partnerRepository.save(persistencePartner);
            HttpHeaders responseHeaders = new HttpHeaders();
            URI newUserUri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("{id}")
                    .buildAndExpand(persistencePartner.getId())
                    .toUri();
            responseHeaders.setLocation(newUserUri);
            return new ResponseEntity<>(persistencePartner, HttpStatus.CREATED);
        }
        catch (Exception exception) {
            return new ResponseEntity<>("Error occur! Partner not added", HttpStatus.UNPROCESSABLE_ENTITY);
        }
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
        try {
            this.partnerRepository.deleteById(partnerId);
            return new ResponseEntity<>( "{\"message\" :\"Partner deleted\"}", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("{\"message\" :\"Error Occur partner not deleted\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
