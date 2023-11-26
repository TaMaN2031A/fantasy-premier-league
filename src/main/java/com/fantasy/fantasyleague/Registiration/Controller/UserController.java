package com.fantasy.fantasyleague.Registiration.Controller;

import com.fantasy.fantasyleague.Registiration.DTO.SignInDTO;
import com.fantasy.fantasyleague.Registiration.DTO.SignUpDTO;
import com.fantasy.fantasyleague.Registiration.Service.MailService;
import com.fantasy.fantasyleague.Registiration.Service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin

@RequestMapping("register/user")
public class UserController {
//gjg
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public String saveUser(@RequestBody SignUpDTO signUpDTO) {
        return userService.addUser(signUpDTO);
    }

    @PostMapping("/signin")
    public boolean signIn(@RequestBody SignInDTO signInDTO) {
        return userService.validateUser(signInDTO);
    }
//    emailDetails are email and username
    @PostMapping("/forgetPassword")
    public ResponseEntity<String> forgetPassword(@RequestBody JsonNode emailDetails){
        return userService.ForgetPassword(emailDetails);
    }
//    passwordUpdateInfo contains email,password,token
    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody JsonNode PasswordUpdateInfo){
        return userService.updatePassword(PasswordUpdateInfo);
    }
}
