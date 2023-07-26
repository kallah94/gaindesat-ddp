package com.gaindesat.ddp.service;

import com.gaindesat.ddp.dto.UserDTO;
import com.gaindesat.ddp.payload.mail.EmailDetails;
import com.gaindesat.ddp.serviceinterface.EmailServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailService implements EmailServiceInterface {

    @Autowired private JavaMailSender mailSender;
    @Value("${spring.mail.username}") private String sender;

    public EmailService() {
    }


    @Override
    public String sendMail(EmailDetails emailDetails) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(this.sender);
            mailMessage.setTo(emailDetails.getRecipient());
            mailMessage.setSubject(emailDetails.getSubject());
            mailMessage.setText(emailDetails.getMsgBody());

            mailSender.send(mailMessage);
            return "Mail Sent Successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String sendFirstConnectionPassword(EmailDetails emailDetails, UserDTO userDTO) {

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");
            String htmlMsg = String.format("<body style='border:2px'>Bonjour Mr/Mme <strong>%s</strong><br>" +
                    "Votre compte d'utilisateur a été bien créé sur notre plateforme.<br>" +
                    "Voici vos information de connexion:<br>" +
                    "Username: <strong>%s</strong> <br>" +
                    "Password: <strong>%s</strong>" +
                    "</body>", userDTO.getFullName(), userDTO.getUsername(), userDTO.getPassword());
            message.setContent(htmlMsg, "text/html");
            helper.setTo(userDTO.getEmail());
            helper.setSubject("Nouveau compte d'utilisateur");
            mailSender.send(message);
            return "Mail send Successfully";
        } catch (Exception e) {
        return e.getMessage();
        }
    }
}
