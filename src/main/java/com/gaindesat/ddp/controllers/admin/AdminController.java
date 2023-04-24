package com.gaindesat.ddp.controllers.admin;

import com.gaindesat.ddp.models.Partner;
import com.gaindesat.ddp.models.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    /**
    @PostMapping("/set-user-partner")
    @Consumes({MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> setPartner(@Valid @RequestBody Long partnerId ) {
        Optional<Partner> optionalPartner = this.
    }
    **/
}
