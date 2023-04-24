package com.gaindesat.ddp.controllers.admin;

import com.gaindesat.ddp.dto.RoleDTO;
import com.gaindesat.ddp.models.Permission;
import com.gaindesat.ddp.repository.PermissionRepository;
import com.gaindesat.ddp.service.PermissionService;
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
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class PermissionController {

    PermissionService roleService;

    @Autowired
    PermissionRepository permissionRepository;

    @GetMapping("/permissions")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Permission>> getAllRoles() {

        Iterable<Permission> allRoles = permissionRepository.findAll();
        return new ResponseEntity<>(allRoles, HttpStatus.OK);
    }

    @GetMapping("/permissions/{roleId}")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUser(@PathVariable UUID roleId) {
        Optional<Permission> optionalRole = permissionRepository.findById(roleId);

        return optionalRole.<ResponseEntity<Object>>map(permission ->
                new ResponseEntity<>(permission, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>("{Role not found !}", HttpStatus.NOT_FOUND));
    }

    @PostMapping("/permissions")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Permission> createRole(@Valid @RequestBody RoleDTO roleDTO) {
        Permission persistencePermission = roleService.populateRole(roleDTO, new Permission());
        this.permissionRepository.save(persistencePermission);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(persistencePermission.getId())
                .toUri();
        responseHeaders.setLocation(newUserUri);
        return new ResponseEntity<>(persistencePermission, HttpStatus.CREATED);
    }

    @PutMapping("/permissions/{roleId}")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateRole(@Valid @RequestBody RoleDTO roleDTO, @PathVariable UUID roleId) {
        Optional<Permission> optionalRole = permissionRepository.findById(roleId);
        if(optionalRole.isPresent()) {
            Permission persistencePermission = roleService.populateRole(roleDTO, optionalRole.get());
            permissionRepository.save(persistencePermission);
            return new ResponseEntity<>(persistencePermission, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/permissions/{roleId}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteRole(@PathVariable UUID roleId) {
        permissionRepository.deleteById(roleId);

        return new ResponseEntity<>("permissions deleted successfully", HttpStatus.OK);
    }
}
