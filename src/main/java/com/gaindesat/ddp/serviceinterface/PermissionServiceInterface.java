package com.gaindesat.ddp.serviceinterface;

import com.gaindesat.ddp.dto.RoleDTO;
import com.gaindesat.ddp.models.Permission;

public interface PermissionServiceInterface {
    Permission populateRole(RoleDTO roleDTO, Permission permission);
}
