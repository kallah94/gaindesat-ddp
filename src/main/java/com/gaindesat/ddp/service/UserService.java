package com.gaindesat.ddp.service;


import com.gaindesat.ddp.dto.UserDTO;
import com.gaindesat.ddp.models.User;
import com.gaindesat.ddp.serviceinterface.UserServiceInterface;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserServiceInterface {

    @Override
    public User populateUser(UserDTO userDTO, User persistenceUser) {
        persistenceUser.setUsername(userDTO.getUsername());
        persistenceUser.setFullName(userDTO.getFullName());
        persistenceUser.setEmail(userDTO.getEmail());
        persistenceUser.setPassword(userDTO.getPassword());
        return persistenceUser;
    }


}
