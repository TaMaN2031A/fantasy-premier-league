package com.fantasy.fantasyleague.Registiration;


import com.fantasy.fantasyleague.Registiration.Model.Mail;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Repository.AdminRepository;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import com.fantasy.fantasyleague.Registiration.Service.MailServiceImplementation;
import com.fantasy.fantasyleague.Registiration.Service.RegistrationServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
@TestPropertySource("classpath:test_local.properties")
@SpringBootTest
public class ForgetPasswordTest {

    @Autowired
    private MailServiceImplementation service;
    @Autowired
    private RegistrationServiceImpl service2;
    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void ForgetPassword1() {
        // send forget password email to a existing gmail
        assertEquals(service.sendForgetPasswordEmail("mohamed.arous940@gmail.com" , "mohaemd_arous" , "1234")
                , "Mail Sent Successfully");
    }


    @Test
    void ForgetPassword2() {
        // send forget password email to a false Email
        assertNotEquals(service.sendForgetPasswordEmail("mohamed.agmail.com" , "mohaemd_arous" , "1234")
                , ResponseEntity.ok("mail sent successfully"));
    }


    @Test
    void ForgetPassword3() {
        // send regular email to a false Email
        Mail mail = new Mail();
        mail.setToEmail("mohamed.agmail.com");
        mail.setUserName("mohaemd_arous");
        mail.setSubject("fantasy league");
        mail.setMessage("welcome to fantasy league");

        assertNotEquals(service.sendEmail( mail)
                , ResponseEntity.ok("mail sent successfully"));
    }

    @Test
    void ForgetPassword4() {
        // send regular email to a existing Email
        Mail mail = new Mail();
        mail.setToEmail("mohamed.arous940@gmail.com");
        mail.setUserName("mohaemd_arous");
        mail.setSubject("fantasy league");
        mail.setMessage("welcome to fantasy league");
        assertEquals(service.sendEmail( mail)
                , ResponseEntity.ok("mail sent successfully"));
    }


    @Test
    void ForgetPassword5() {
        // send regular email to a existing Email
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode emailDetails = objectMapper.createObjectNode()
                .put("email", "mohamed.arous164@gmail.com");
        assertEquals("User does not exist" , service2.ForgetPassword(emailDetails).toString());
    }

    @Test
    void ForgetPassword6() {
        // send regular email to a existing Email
        User signup = new User();
        signup.setFirstName("mohamed1");
        signup.setLastName("arous1");
        signup.setEmail("mohamed.arous940@gmail.com");
        signup.setPassword("12259861");
        signup.setUserName("mohamed_arous1");
        service2.addUser(signup);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode emailDetails = objectMapper.createObjectNode()
                .put("email", "mohamed.arous940@gmail.com");

        assertEquals("Mail Sent Successfully" , service2.ForgetPassword(emailDetails).toString());
    }

    @Test
    void ForgetPassword7() {
        // send regular email to a existing Email
        User signup = new User();
        signup.setFirstName("amr");
        signup.setLastName("eagle");
        signup.setEmail("amr_eagle1321mail.com");
        signup.setPassword("12259861");
        signup.setUserName("amr_eagle");
        service2.addUser(signup);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode emailDetails = objectMapper.createObjectNode()
                .put("email", "amr_eagle1321mail.com");

        assertEquals("Email is wrong" , service2.ForgetPassword(emailDetails).toString());
    }


    @Test
    void ForgetPassword8() {

        User signup = new User();
        signup.setFirstName("mohamed1");
        signup.setLastName("arous1");
        signup.setEmail("mohamed.arous177@gmail.com");
        signup.setPassword("12259861");
        signup.setUserName("mohamed_arous177");
        service2.addUser(signup);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode emailDetails = objectMapper.createObjectNode()
                .put("email", "mohamed.arous177@gmail.com");
        service2.ForgetPassword(emailDetails);
        User user  = userRepo.findByEmail("mohamed.arous177@gmail.com").orElseThrow();
        JsonNode PasswordUpdateInfo = objectMapper.createObjectNode()
                .put("email", "mohamed.arous177@gmail.com")
                .put("token"  , user.getToken().toString() )
                .put("password" , "newwwww12");
        assertEquals("password update successful" , service2.updatePassword(PasswordUpdateInfo).toString());
        user  = userRepo.findByEmail("mohamed.arous177@gmail.com").orElseThrow();
        assertTrue(service2.checkPassword("newwwww12"  , user.getPassword()));
        assertFalse(service2.checkPassword("12259861"  , user.getPassword()));


    }


    @Test
    void ForgetPassword9() {

        User signup = new User();
        signup.setFirstName("mohamed1");
        signup.setLastName("arous1");
        signup.setEmail("mohamed.arous188@gmail.com");
        signup.setPassword("12259861");
        signup.setUserName("mohamed_arous188");
        service2.addUser(signup);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode emailDetails = objectMapper.createObjectNode()
                .put("email", "mohamed.arous188@gmail.com");
        service2.ForgetPassword(emailDetails);
        User user  = userRepo.findByEmail("mohamed.arous188@gmail.com").orElseThrow();
        JsonNode PasswordUpdateInfo = objectMapper.createObjectNode()
                .put("email", "mohamed.arous188@gmail.com")
                .put("token"  , "123456789" )
                .put("password" , "newwwww12");
        assertEquals("Invalid Token Try again" , service2.updatePassword(PasswordUpdateInfo).toString());
        User user = userRepo.findByEmail("mohamed.arous188@gmail.com").orElseThrow();
        assertFalse(service2.checkPassword("newwwww12"  , user.getPassword()));
        assertTrue(service2.checkPassword("12259861"  , user.getPassword()));
    }


}

