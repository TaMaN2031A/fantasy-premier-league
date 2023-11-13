package com.fantasy.fantasyleague.Registiration.Service;

import com.fantasy.fantasyleague.Registiration.DTO.SignUpDTO;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    final String emailFound = "The email you provided is currently in use. Please pick another one.";
    final String userNameFound = "The username you provided is currently in use. Please pick another one.";
    final String success = "Registered Successfully";

    private Boolean checkEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

    private Boolean checkUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        return user != null;
    }

    @Override
    public String addUser(SignUpDTO signUpDTO) {
        if(checkEmail(signUpDTO.getEmail()))
            return emailFound;

        if(checkUserName(signUpDTO.getUserName()))
            return userNameFound;

        User user = signUpDTO.mapToUser();
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return success;
    }
}
