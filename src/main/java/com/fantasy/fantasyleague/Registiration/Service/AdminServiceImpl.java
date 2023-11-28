package com.fantasy.fantasyleague.Registiration.Service;

import com.fantasy.fantasyleague.Registiration.DTO.SignInDTO;
import com.fantasy.fantasyleague.Registiration.Model.Admin;
import com.fantasy.fantasyleague.Registiration.Repository.AdminRepository;
import com.fasterxml.jackson.databind.JsonNode;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private  final MailService mailService;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, PasswordEncoder passwordEncoder, MailService mailService) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    private String encodePassword(String password) {
        return this.passwordEncoder.encode(password);
    }


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

    @Override
    public ResponseEntity<String> updatePassword(JsonNode PasswordUpdateInfo){
        try{
            String email = PasswordUpdateInfo.get("email").asText();
            String password = PasswordUpdateInfo.get("password").asText();
            String token = PasswordUpdateInfo.get("token").asText();
            Admin admin = adminRepository.findByEmail(email);
            if(admin==null)
                return ResponseEntity.notFound().build(); // No custom message
            if(admin.getToken().isEmpty()){
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("didn't enter email in first place");
            }
            if(!token.equals(admin.getToken())){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }
            admin.setPassword(encodePassword(password));
            admin.setToken("");
            adminRepository.save(admin);
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
            String token = RandomString.make(8);

            Admin admin =adminRepository.findByEmail(mail);
            if(admin==null)
                return ResponseEntity.notFound().build(); // No custom message

            if(isResponseEntityOk(mailService.sendForgetPasswordEmail(mail,admin.getUserName(),token))){
                admin.setToken(token);
                adminRepository.save(admin);
                return ResponseEntity.ok("email sent");
            }else {
                return new ResponseEntity<>("An error occurred:", HttpStatus.INTERNAL_SERVER_ERROR);
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
