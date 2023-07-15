package com.gaindesat.ddp.service;


import com.gaindesat.ddp.dto.RoleDTO;
import com.gaindesat.ddp.models.Category;
import com.gaindesat.ddp.models.Permission;
import com.gaindesat.ddp.serviceinterface.PermissionServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class PermissionService implements PermissionServiceInterface {
    @Override
    public Permission populateRole(RoleDTO roleDTO, Permission persistencePermission, Category category) {
        persistencePermission.setCode(roleDTO.getCode());
        persistencePermission.setTitle(roleDTO.getTitle());
        persistencePermission.setCategory(category);
        return persistencePermission;
    }

    @Override
    public Permission populateRole(RoleDTO roleDTO, Permission permission) {
        permission.setCode(roleDTO.getCode());
        permission.setTitle(roleDTO.getTitle());
        return permission;
    }
}
