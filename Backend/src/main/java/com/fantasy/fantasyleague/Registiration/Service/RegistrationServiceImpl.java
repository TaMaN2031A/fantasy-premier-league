package com.fantasy.fantasyleague.Registiration.Service;

import com.fantasy.fantasyleague.Registiration.DTO.GoogleDTO;
import com.fantasy.fantasyleague.Registiration.DTO.SignInDTO;
import com.fantasy.fantasyleague.Registiration.Model.*;
import com.fantasy.fantasyleague.Registiration.Repository.AdminRepository;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Person findEntity(String email, String userName, Role role) {
       return role == Role.ADMIN ?
            adminRepository.findByEmailOrUserName(email, userName):
            userRepository.findByEmailOrUserName(email, userName);
    }

    public Boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public String addUser(User user) {

        Person retrievedUser = findEntity(user.getEmail(), user.getUserName(), Role.USER);
        Person retrievedAdmin = findEntity(user.getEmail(), user.getUserName(), Role.ADMIN);

        if(retrievedUser != null || retrievedAdmin != null)
            return Response.dataExist;

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return Response.signUpSuccessfully;

    }

    @Override
    public String validate(SignInDTO signInDTO) {

        Person entity = findEntity(signInDTO.getUserNameOrEmail(), signInDTO.getUserNameOrEmail(), signInDTO.getRole());

        if(entity == null)
            return Response.noUser;

        Boolean response = checkPassword(signInDTO.getPassword(), entity.getPassword());
        return response == Boolean.TRUE ? Response.signUpSuccessfully : Response.wrongCredentials;
    }

    @Override
    public String validateGoogle(GoogleDTO googleDTO) {
        return googleDTO.getRole() == Role.ADMIN ?
                validateAdminUsingGoogle(googleDTO.getEmail()):
                validateUserUsingGoogle(googleDTO);
    }

    private String validateUserUsingGoogle(GoogleDTO googleDTO) {

        Optional<User> retrievedUser = userRepository.findByEmail(googleDTO.getEmail());
        if(retrievedUser.isEmpty()) {
            User user = googleDTO.userMapper(this.passwordEncoder.encode(RandomString.make(8)));
            userRepository.save(user);
        }
        return Response.loginSuccessfully;
    }

    private String validateAdminUsingGoogle(String email) {
        Optional<Admin> retrievedAdmin = adminRepository.findByEmail(email);
        if(retrievedAdmin.isPresent())
            return Response.loginSuccessfully;
        return Response.noUser;
    }

}
