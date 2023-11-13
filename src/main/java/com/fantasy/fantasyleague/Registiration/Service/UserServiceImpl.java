package com.fantasy.fantasyleague.Registiration.Service;

import com.fantasy.fantasyleague.Registiration.DTO.SignInDTO;
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
    final String loginFail = "Sorry, we couldn't verify your credentials. Try again.";
    final String success = "Registered Successfully";

    private Boolean checkEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

    private Boolean checkUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        return user != null;
    }

    private String encodePassword(String password) {
        return this.passwordEncoder.encode(password);
    }


    @Override
    public String addUser(SignUpDTO signUpDTO) {
        if(checkEmail(signUpDTO.getEmail()))
            return emailFound;

        if(checkUserName(signUpDTO.getUserName()))
            return userNameFound;

        User user = signUpDTO.mapToUser(this.passwordEncoder.encode(signUpDTO.getPassword()));
        userRepository.save(user);
        return success;
    }

    private User getUser(SignInDTO signInDTO) {
        User user = userRepository.findByEmail(signInDTO.getUserNameOrEmail());

        if(user == null)
            user = userRepository.findByUserName(signInDTO.getUserNameOrEmail());

        return user;
    }

    private Boolean checkPassword(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public String validateUser(SignInDTO signInDTO) {
        User user = getUser(signInDTO);

        if(user == null)
            return loginFail;

        if(checkPassword(user, signInDTO.getPassword()))
            return success;

        return loginFail;
    }

}
