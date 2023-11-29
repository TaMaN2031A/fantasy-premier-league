package com.fantasy.fantasyleague.Registiration.Service;

import com.fantasy.fantasyleague.Registiration.Model.Mail;
import org.springframework.http.ResponseEntity;

public interface MailService {
    public String sendForgetPasswordEmail(String toEmail, String userName,  String token);

     public ResponseEntity<String> sendEmail(Mail mail);
}
