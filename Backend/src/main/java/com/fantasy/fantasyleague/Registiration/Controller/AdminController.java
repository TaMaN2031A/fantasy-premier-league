package com.fantasy.fantasyleague.Registiration.Controller;

import com.fantasy.fantasyleague.Registiration.DTO.SignInDTO;
import com.fantasy.fantasyleague.Registiration.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("register/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @PostMapping("/signin")
    public String signin(@RequestBody SignInDTO signInDTO) {
        String response = adminService.validateAdmin(signInDTO);
        System.out.println(response);
        return response;
    }
}
