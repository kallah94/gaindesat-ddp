package com.gaindesat.ddp.service;


import com.gaindesat.ddp.dto.PermissionDTO;
import com.gaindesat.ddp.models.Category;
import com.gaindesat.ddp.models.Permission;
import com.gaindesat.ddp.serviceinterface.PermissionServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class PermissionService implements PermissionServiceInterface {
    @Override
    public Permission populateRole(PermissionDTO permissionDTO, Permission persistencePermission, Category category) {
        persistencePermission.setCode(permissionDTO.getCode());
        persistencePermission.setTitle(permissionDTO.getTitle());
        persistencePermission.setCategory(category);
        return persistencePermission;
    }

    @Override
    public Permission populateRole(PermissionDTO permissionDTO, Permission permission) {
        permission.setCode(permissionDTO.getCode());
        permission.setTitle(permissionDTO.getTitle());
        return permission;
    }
}
