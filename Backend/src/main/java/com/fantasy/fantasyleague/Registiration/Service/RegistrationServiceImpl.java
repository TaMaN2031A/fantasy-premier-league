package com.fantasy.fantasyleague.Registiration.Service;

import com.fantasy.fantasyleague.Registiration.DTO.GoogleDTO;
import com.fantasy.fantasyleague.Registiration.DTO.SignInDTO;
import com.fantasy.fantasyleague.Registiration.Model.*;
import com.fantasy.fantasyleague.Registiration.Repository.AdminRepository;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;

import com.fasterxml.jackson.databind.JsonNode;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    final
    UserRepository userRepository;

    final
    AdminRepository adminRepository;

    final
    PasswordEncoder passwordEncoder;

    final
    MailService mailService;

    @Autowired
    public RegistrationServiceImpl(UserRepository userRepository, AdminRepository adminRepository, PasswordEncoder passwordEncoder, MailService mailService) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }


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

    @Override
    public ResponseEntity<String> updatePassword(JsonNode PasswordUpdateInfo) {
        try{
            String mail = PasswordUpdateInfo.get("email").asText();
            String password = PasswordUpdateInfo.get("password").asText();
            String token = PasswordUpdateInfo.get("token").asText();
            Person retrievedUser = findEntity(mail, mail, Role.USER);
            Person retrievedAdmin = findEntity(mail, mail , Role.ADMIN);
            if(retrievedAdmin==null&&retrievedUser==null)
                return ResponseEntity.notFound().build();

            Person user = retrievedUser==null?retrievedAdmin:retrievedUser;

            if(user.getToken().isEmpty())
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("didn't enter email in first place");

            if(!token.equals(user.getToken()))
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");

            user.setPassword(this.passwordEncoder.encode(password));
            user.setToken("");
            if(retrievedUser==null)
                adminRepository.save((Admin) user);
            else
                userRepository.save((User) user);

            return ResponseEntity.ok("password updated successfully");
        }
        catch (Exception e){
            return new ResponseEntity<>("An error occurred: password update failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> ForgetPassword(JsonNode emailDetails) {
       try {
           String mail = emailDetails.get("email").asText();
           String token = RandomString.make(8);
           Person retrievedUser = findEntity(mail, mail, Role.USER);
           Person retrievedAdmin = findEntity(mail, mail, Role.ADMIN);

           if(retrievedUser == null && retrievedAdmin == null)
               return ResponseEntity.notFound().build();

           String userName= retrievedUser==null? retrievedAdmin.getUserName() : retrievedUser.getUserName();
           Person user = retrievedUser==null?retrievedAdmin:retrievedUser;
           if(isResponseEntityOk(mailService.sendForgetPasswordEmail(mail,userName,token))){
               user.setToken(token);
               if (retrievedUser == null) {
                   adminRepository.save((Admin) user);
               } else {
                   userRepository.save((User) user);
               }
               return ResponseEntity.ok("email sent");
           }else {
               return new ResponseEntity<>("Failed:Mail not sent", HttpStatus.INTERNAL_SERVER_ERROR);
           }
       }
       catch (Exception e){
           return new ResponseEntity<>("An error occurred:", HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    private boolean isResponseEntityOk(ResponseEntity<String> responseEntity) {
        return responseEntity.getStatusCode() == HttpStatus.OK;
    }

}
