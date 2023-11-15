package com.fantasy.fantasyleague.Registiration.Controller;

import com.fantasy.fantasyleague.Registiration.DTO.SignInDTO;
import com.fantasy.fantasyleague.Registiration.DTO.SignUpDTO;
import com.fantasy.fantasyleague.Registiration.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("register/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String saveUser(@RequestBody SignUpDTO signUpDTO) {
        String response = userService.addUser(signUpDTO);
        System.out.println(response);
        return response;
    }

    @PostMapping("/signin")
    public String signIn(@RequestBody SignInDTO signInDTO) {
        String response = userService.validateUser(signInDTO);
        System.out.println(response);
        return response;
    }


}
