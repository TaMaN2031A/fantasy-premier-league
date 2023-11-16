package com.fantasy.fantasyleague.Registiration.Service;

import com.fantasy.fantasyleague.Registiration.DTO.SignInDTO;
import com.fantasy.fantasyleague.Registiration.DTO.SignUpDTO;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    final String emailFound = "The email you provided is currently in use. Please pick another one.";
    final String userNameFound = "The username you provided is currently in use. Please pick another one.";
//    final String loginFail = "Sorry, we couldn't verify your credentials. Try again.";
    final String success = "Registered Successfully";

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, MailService mailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    private Boolean checkEmail(String email) {
        return userRepository.findByEmail(email)!=null;
    }

    private Boolean checkUserName(String userName) {
        return userRepository.findByUserName(userName)!=null;
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
    public boolean validateUser(SignInDTO signInDTO) {
        User user = getUser(signInDTO);

        if(user == null)
            return false;

        return checkPassword(user, signInDTO.getPassword());
    }

    @Override
    public ResponseEntity<String> updatePassword(JsonNode PasswordUpdateInfo){
        try{
        String email = PasswordUpdateInfo.get("email").asText();
        String password = PasswordUpdateInfo.get("password").asText();
        String token = PasswordUpdateInfo.get("token").asText();
        User user = userRepository.findByEmail(email);
        if(user==null)
            return ResponseEntity.notFound().build(); // No custom message
        if(user.getToken().isEmpty()){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("didn't enter email in first place");
        }
        if(!token.equals(user.getToken())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
        user.setPassword(encodePassword(password));
        userRepository.save(user);
        return ResponseEntity.ok("password updated successfully");
        }
        catch(Exception e){
            return new ResponseEntity<>("An error occurred:", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public ResponseEntity<String> ForgetPassword(JsonNode emailDetails){
        try{
            String mail=emailDetails.get("email").asText();
            String userName=emailDetails.get("userName").asText();
            String token = RandomString.make(8);

            User user =userRepository.findByEmail(mail);
            if(user==null)
                 return ResponseEntity.notFound().build(); // No custom message

            if(isResponseEntityOk(mailService.sendForgetPasswordEmail(mail,userName,token))){
                user.setToken(token);
                userRepository.save(user);
                return ResponseEntity.ok("email sent");
            }else {
                return new ResponseEntity<>("An error occurred: mail is not sent", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private boolean isResponseEntityOk(ResponseEntity<String> responseEntity) {
        return responseEntity.getStatusCode() == HttpStatus.OK;
    }
}
