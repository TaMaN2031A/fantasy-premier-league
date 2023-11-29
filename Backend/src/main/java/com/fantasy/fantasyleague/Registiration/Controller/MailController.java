package com.fantasy.fantasyleague.Registiration.Controller;

import com.fantasy.fantasyleague.Registiration.Model.Mail;
import com.fantasy.fantasyleague.Registiration.Service.MailServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/mail")
public class MailController {
    private final MailServiceImplementation mailService;

    @Autowired
    public MailController(MailServiceImplementation mailService) {
        this.mailService = mailService;
    }
    @PostMapping("/send")
    public Object sendEmail(@RequestBody Mail ToBeSent){
        return  mailService.sendEmail(ToBeSent);
    }
}
