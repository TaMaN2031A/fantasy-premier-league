package com.fantasy.fantasyleague.Registiration.Service;

import com.fantasy.fantasyleague.Registiration.DTO.SignInDTO;
import com.fantasy.fantasyleague.Registiration.Model.Admin;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Repository.AdminRepository;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    final String loginFail = "Sorry, we couldn't verify your credentials. Try again.";
    final String success = "Registered Successfully";

    private Admin getAdmin(SignInDTO signInDTO) {
        Admin admin = adminRepository.findByEmail(signInDTO.getUserNameOrEmail());

        if(admin == null)
            admin = adminRepository.findByUserName(signInDTO.getUserNameOrEmail());

        return admin;
    }

    private Boolean checkPassword(Admin admin, String password) {
        return passwordEncoder.matches(password, admin.getPassword());
    }

    @Override
    public String validateAdmin(SignInDTO signInDTO) {
        Admin admin = getAdmin(signInDTO);

        if(admin == null)
            return loginFail;

        if(checkPassword(admin, signInDTO.getPassword()))
            return success;

        return loginFail;
    }
}
