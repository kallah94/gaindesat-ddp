package com.gaindesat.ddp.controllers.admin;

import com.gaindesat.ddp.dto.UserCategoryDTO;
import com.gaindesat.ddp.dto.UserDTO;
import com.gaindesat.ddp.dto.UserPartnerDTO;
import com.gaindesat.ddp.models.Category;
import com.gaindesat.ddp.models.Partner;
import com.gaindesat.ddp.models.User;
import com.gaindesat.ddp.repository.CategoryRepository;
import com.gaindesat.ddp.repository.PartnerRepository;
import com.gaindesat.ddp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import org.xml.sax.EntityResolver;

import javax.servlet.http.Part;
import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response;
import java.util.Optional;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    PartnerRepository partnerRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("/set-user-partner")
    @Consumes({MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> setPartner(@Valid @RequestBody UserPartnerDTO userPartnerDTO ) {
        Optional<User> optionalUser = this.userRepository
                .findById(userPartnerDTO.getUserUuid());

        if(optionalUser.isPresent()) {
            Optional<Partner> optionalPartner = this.partnerRepository.findById(userPartnerDTO.getPartnerUuid());
            if(optionalPartner.isPresent()) {
                optionalUser.get().setPartner(optionalPartner.get());
                userRepository.save(optionalUser.get());
                return ResponseEntity.ok("User Partner set !!!");
            } else {
                return new ResponseEntity<>("Partner with this id not found !!! "+ userPartnerDTO.getPartnerUuid(), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("User with this id not found !!! "+ userPartnerDTO.getUserUuid(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/set-user-category")
    @Consumes({MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> setCategory(@Valid @RequestBody UserCategoryDTO userCategoryDTO) {
        Optional<User> optionalUser = this.userRepository
                .findById(userCategoryDTO.getUserUuid());

        if(optionalUser.isPresent()) {
            Optional<Category> optionalCategory = categoryRepository.findById(userCategoryDTO.getCategoryUuid());
            if(optionalCategory.isPresent()) {
                optionalUser.get().setCategory(optionalCategory.get());
                userRepository.save(optionalUser.get());
                return ResponseEntity.ok("User category set!!");
            } else {
                return new ResponseEntity<>("Category with this id not found "+userCategoryDTO.getCategoryUuid(), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("User with this id not found !!! "+ userCategoryDTO.getUserUuid(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/update-user-status")
    @Consumes({MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateUserStatus(@Valid @RequestBody UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(userDTO.getUuid());

        if(optionalUser.isPresent()) {
            optionalUser.get().setStatus(userDTO.isStatus());
            userRepository.save(optionalUser.get());
            return ResponseEntity.ok("Status change successfully");
        } else {
            return new ResponseEntity<>("error", HttpStatus.NOT_MODIFIED);
        }
    }
}
