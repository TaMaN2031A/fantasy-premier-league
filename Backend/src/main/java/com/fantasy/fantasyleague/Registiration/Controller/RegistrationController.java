package com.fantasy.fantasyleague.Registiration.Controller;

import com.fantasy.fantasyleague.Registiration.DTO.GoogleDTO;
import com.fantasy.fantasyleague.Registiration.DTO.SignInDTO;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Service.RegistrationService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("register/")
public class RegistrationController {

    final
    RegistrationService registrationService;
    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

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
    @PostMapping("/ForgetPassword")
    public ResponseEntity<String> forgetPassword(@RequestBody JsonNode emailDetails){
        return registrationService.ForgetPassword(emailDetails);
    }
    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody JsonNode PasswordUpdateInfo){
        return registrationService.updatePassword(PasswordUpdateInfo);
    }
}