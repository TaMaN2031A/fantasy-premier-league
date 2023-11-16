package com.fantasy.fantasyleague.Registiration.Service;

import com.fantasy.fantasyleague.Registiration.Model.Mail;
import com.fantasy.fantasyleague.Registiration.Repository.AdminRepository;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImplementation implements MailService{

    private final JavaMailSender javaMailSender;

    @Autowired
    public MailServiceImplementation(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;

    }

    @Override
    public ResponseEntity<String> sendEmail(Mail mailToBeSent){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(mailToBeSent.getToEmail());
            mailMessage.setSubject(mailToBeSent.getSubject());
            mailMessage.setText("Dear "+mailToBeSent.getUserName()+"\n\n"+mailToBeSent.getSubject());
            mailMessage.setFrom("fantasyteamcsed@gmail.com");
            javaMailSender.send(mailMessage);
            return ResponseEntity.ok("mail sent successfully");
        }
        catch (Exception e){
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> sendForgetPasswordEmail(String toEmail, String userName,String token) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            String message = "Dear "+userName+"\n\n"+"Your password changing code is \n"+token+"\nlink of page to front +email encrypted"+toEmail;
            mailMessage.setTo(toEmail);
            mailMessage.setSubject("Forgetting Password");
            mailMessage.setText("Dear "+userName+"\n\n"+message);
            mailMessage.setFrom("fantasyteamcsed@gmail.com");
            javaMailSender.send(mailMessage);
            return ResponseEntity.ok("mail sent successfully");
        }
        catch (Exception e){
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
