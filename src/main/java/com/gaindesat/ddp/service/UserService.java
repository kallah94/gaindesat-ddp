package com.gaindesat.ddp.service;


import com.gaindesat.ddp.dto.UserDTO;
import com.gaindesat.ddp.models.User;
import com.gaindesat.ddp.repository.UserRepository;
import com.gaindesat.ddp.serviceinterface.UserServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;

    private ModelMapper mapper;
    @Override
    public User populateUser(UserDTO userDTO, User persistenceUser) {
        persistenceUser.setUsername(userDTO.getUsername());
        persistenceUser.setFullName(userDTO.getFullName());
        persistenceUser.setEmail(userDTO.getEmail());
        persistenceUser.setPassword(userDTO.getPassword());
        return persistenceUser;
    }


}
