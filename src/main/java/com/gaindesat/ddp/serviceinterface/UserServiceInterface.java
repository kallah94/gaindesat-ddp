package com.gaindesat.ddp.serviceinterface;

import com.gaindesat.ddp.dto.UserDTO;
import com.gaindesat.ddp.models.User;

public interface UserServiceInterface {

    User populateUser(UserDTO userDTO, User user);

}
