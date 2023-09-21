package com.gaindesat.ddp.service;

import com.gaindesat.ddp.dto.UserDTO;
import com.gaindesat.ddp.models.Category;
import com.gaindesat.ddp.models.Partner;
import com.gaindesat.ddp.models.User;
import com.gaindesat.ddp.serviceinterface.UserServiceInterface;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserServiceInterface {
    @Autowired
    PasswordEncoder encoder;
    @Override
    public User populateUser(UserDTO userDTO, User persistenceUser, Category category, Partner partner) {
        persistenceUser.setUsername(userDTO.getUsername());
        persistenceUser.setFullName(userDTO.getFullName());
        persistenceUser.setEmail(userDTO.getEmail());
        persistenceUser.setPassword(encoder.encode(userDTO.getPassword()));
        persistenceUser.setStatus(userDTO.isStatus());
        persistenceUser.setCategory(category);
        persistenceUser.setPartner(partner);
        return persistenceUser;
    }

    @Override
    public User populateUser(UserDTO userDTO, User persistenceUser) {
        persistenceUser.setUsername(userDTO.getUsername());
        persistenceUser.setFullName(userDTO.getFullName());
        persistenceUser.setEmail(userDTO.getEmail());
        persistenceUser.setPassword(userDTO.getPassword());
        return persistenceUser;
    }

    @Override
    public String randomPasswordGenerator() {
        RandomString randomString = new RandomString(16);
        return randomString.nextString();
    }
}
