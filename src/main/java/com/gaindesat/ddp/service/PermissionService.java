package com.gaindesat.ddp.service;


import com.gaindesat.ddp.dto.RoleDTO;
import com.gaindesat.ddp.models.Permission;
import com.gaindesat.ddp.serviceinterface.PermissionServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class PermissionService implements PermissionServiceInterface {
    @Override
    public Permission populateRole(RoleDTO roleDTO, Permission persistencePermission) {
        persistencePermission.setCode(roleDTO.getCode());
        persistencePermission.setTitle(roleDTO.getTitle());
        return persistencePermission;
    }
}
