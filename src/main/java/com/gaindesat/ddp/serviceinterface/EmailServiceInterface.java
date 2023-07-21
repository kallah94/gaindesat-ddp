package com.gaindesat.ddp.serviceinterface;

import com.gaindesat.ddp.payload.mail.EmailDetails;


public interface EmailServiceInterface {

    String sendMail(EmailDetails emailDetails);
}
