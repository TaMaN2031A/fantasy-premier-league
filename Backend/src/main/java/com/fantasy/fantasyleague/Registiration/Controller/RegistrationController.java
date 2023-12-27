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
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        return registrationService.addUser(user);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestBody SignInDTO signInDTO) {
        return registrationService.validate(signInDTO);
    }

    @PostMapping("/oauth/google")
    public ResponseEntity<String> signInWithGoogle(@RequestBody GoogleDTO googleDTO) {
        return registrationService.validateGoogle(googleDTO);
    }
    @PostMapping("/ForgetPassword")
    public String forgetPassword(@RequestBody JsonNode emailDetails){
        return registrationService.ForgetPassword(emailDetails);
    }
    @PostMapping("/updatePassword")
    public String updatePassword(@RequestBody JsonNode PasswordUpdateInfo){
        return registrationService.updatePassword(PasswordUpdateInfo);
    }

}