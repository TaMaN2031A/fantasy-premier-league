package com.fantasy.fantasyleague.Registiration.Service;


import com.fantasy.fantasyleague.Registiration.Model.Mail;
import org.springframework.http.ResponseEntity;

public interface MailService {
    String sendForgetPasswordEmail(String toEmail, String userName,  String token);

    ResponseEntity<String> sendEmail(Mail mail);
}
