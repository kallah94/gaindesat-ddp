package com.gaindesat.ddp.controllers.admin;

import com.gaindesat.ddp.dto.FullUserDTO;
import com.gaindesat.ddp.dto.UserCategoryDTO;
import com.gaindesat.ddp.dto.UserDTO;
import com.gaindesat.ddp.dto.UserPartnerDTO;
import com.gaindesat.ddp.models.Category;
import com.gaindesat.ddp.models.Partner;
import com.gaindesat.ddp.models.Permission;
import com.gaindesat.ddp.models.User;
import com.gaindesat.ddp.payload.mail.EmailDetails;
import com.gaindesat.ddp.repository.CategoryRepository;
import com.gaindesat.ddp.repository.PartnerRepository;
import com.gaindesat.ddp.repository.UserRepository;
import com.gaindesat.ddp.service.EmailService;
import com.gaindesat.ddp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("api/v1/admin")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    EmailService emailService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PartnerRepository partnerRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/users")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<FullUserDTO>> getAllUsers() {

        Iterable<User> allUsers = this.userRepository.findAll();
        List<FullUserDTO> userDTOS = new ArrayList<>();
        allUsers.forEach(user -> {
            FullUserDTO fullUserDTO = new FullUserDTO(
                    user.getUuid(),
                    user.isStatus(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getFullName(),
                    user.getPartner().getPartName(),
                    user.getCategory().getCatName(),
                    user.getCategory().getPermissions().stream().map(
                            Permission::getTitle
                    ).collect(Collectors.toList())
            );
            userDTOS.add(fullUserDTO);
        });
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUser(@PathVariable UUID userId) {
        Optional<User> optionalUser = this.userRepository.findById(userId);

        return optionalUser.<ResponseEntity<Object>>map(user ->
                new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>("{User not found}", HttpStatus.NOT_FOUND));
    }
    @PostMapping("/users")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FullUserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        userDTO.setPassword(userService.randomPasswordGenerator());
        Optional<Category> category = categoryRepository.findById(userDTO.getCategoryUUID());
        Optional<Partner> partner = partnerRepository.findById(userDTO.getPartnerUUID());

        if(category.isPresent() && partner.isPresent() ) {
            User persistenceUser = userService.populateUser(userDTO, new User(), category.get(), partner.get());
            userRepository.save(persistenceUser);
            emailService.sendFirstConnectionPassword(new EmailDetails(), userDTO);
            HttpHeaders responseHeaders = new HttpHeaders();
            URI newUserUri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("{id}")
                    .buildAndExpand(persistenceUser.getUuid())
                    .toUri();
            responseHeaders.setLocation(newUserUri);
            UserDTO responseUser = new UserDTO(
                    persistenceUser.getUuid(),
                    persistenceUser.getUsername(),
                    persistenceUser.getEmail(),
                    persistenceUser.getFullName(),
                    persistenceUser.isStatus()
            );
            FullUserDTO fullUserDTO = new FullUserDTO(
                    persistenceUser.getUuid(),
                    persistenceUser.isStatus(),
                    persistenceUser.getUsername(),
                    persistenceUser.getEmail(),
                    persistenceUser.getFullName(),
                    persistenceUser.getPartner().getPartName(),
                    persistenceUser.getCategory().getCatName(),
                    persistenceUser.getCategory().getPermissions().stream().map(Permission::getTitle).collect(Collectors.toList())
            );
            return new ResponseEntity<>(fullUserDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new FullUserDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/users/{userId}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable UUID userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            User persistenceUser = userService.populateUser(userDTO, optionalUser.get());
            userRepository.save(persistenceUser);

            return new ResponseEntity<>(persistenceUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not found !", HttpStatus.NOT_MODIFIED);
        }
    }

    @PostMapping("/update-user-status")
    @Consumes({MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> updateUserStatus(@Valid @RequestBody UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(userDTO.getUuid());

        if(optionalUser.isPresent()) {
            optionalUser.get().setStatus(userDTO.isStatus());
            userRepository.save(optionalUser.get());
            return ResponseEntity.ok("Status change successfully");
        } else {
            return new ResponseEntity<>("error", HttpStatus.NOT_MODIFIED);
        }
    }

    @PostMapping("/set-user-category")
    @Consumes({MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> setCategory(@Valid @RequestBody UserCategoryDTO userCategoryDTO) {
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
    @DeleteMapping("/users/{userId}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteUser(@PathVariable UUID userId) {
        userRepository.deleteById(userId);
        return new ResponseEntity<>("user removed successfully", HttpStatus.OK);
    }
}
