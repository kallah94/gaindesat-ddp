package com.gaindesat.ddp.serviceinterface;

import com.gaindesat.ddp.dto.UserDTO;
import com.gaindesat.ddp.payload.mail.EmailDetails;


public interface EmailServiceInterface {

    String sendMail(EmailDetails emailDetails);

    String sendFirstConnectionPassword(EmailDetails emailDetails, UserDTO userDTO);
}
