package com.gaindesat.ddp.serviceinterface;

import com.gaindesat.ddp.dto.PermissionDTO;
import com.gaindesat.ddp.models.Category;
import com.gaindesat.ddp.models.Permission;

public interface PermissionServiceInterface {
    Permission populateRole(PermissionDTO permissionDTO, Permission permission, Category category);

    Permission populateRole(PermissionDTO permissionDTO, Permission permission);
}
