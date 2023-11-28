package com.fantasy.fantasyleague.Registiration.Controller;

import com.fantasy.fantasyleague.Registiration.DTO.GoogleDTO;
import com.fantasy.fantasyleague.Registiration.DTO.SignInDTO;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("register/")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping("/sign-up")
    public String saveUser(@RequestBody User user) {
        String response = registrationService.addUser(user);
        System.out.println(response);
        return response;
    }

    @PostMapping("/sign-in")
    public String signIn(@RequestBody SignInDTO signInDTO) {
        String response = registrationService.validate(signInDTO);
        System.out.println(response);
        return response;
    }

    @PostMapping("/oauth/google")
    public String signInWithGoogle(@RequestBody GoogleDTO googleDTO) {
        String response = registrationService.validateGoogle(googleDTO);
        System.out.println(response);
        return response;
    }

}