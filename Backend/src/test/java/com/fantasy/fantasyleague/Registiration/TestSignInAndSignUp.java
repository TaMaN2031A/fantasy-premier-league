package com.fantasy.fantasyleague.Registiration;


import com.fantasy.fantasyleague.Registiration.DTO.GoogleDTO;
import com.fantasy.fantasyleague.Registiration.DTO.SignInDTO;

import com.fantasy.fantasyleague.Registiration.Model.Admin;
import com.fantasy.fantasyleague.Registiration.Model.Role;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Repository.AdminRepository;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import com.fantasy.fantasyleague.Registiration.Service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
@TestPropertySource("classpath:test_local.properties")
@SpringBootTest
public class TestSignInAndSignUp {

    @Autowired
    private RegistrationService service;

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void checkSignInAndSignUp1() {
        // user sign in using username
        SignInDTO signin = new SignInDTO();
        User signup = new User();
        signin.setUserNameOrEmail("mohamed_arous1");
        signin.setPassword("12259861");
        signin.setRole(Role.USER);
        signup.setFirstName("mohamed1");
        signup.setLastName("arous1");
        signup.setEmail("mohamed.arous9401@gmail.com");
        signup.setPassword("12259861");
        signup.setUserName("mohamed_arous1");
        assertEquals(service.addUser(signup), "Sign up successful");
        assertEquals(service.validate(signin) , "Login successful");
    }

    @Test
    void checkSignInAndSignUp2() {
        // admin sign in using username
        SignInDTO signin = new SignInDTO();
        Admin admin = new Admin();
        signin.setUserNameOrEmail("mohamed_arous");
        signin.setPassword("12259861");
        signin.setRole(Role.ADMIN);
        admin.setFirstName("mohamed1");
        admin.setLastName("arous1");
        admin.setEmail("mohamed.arous@gmail.com");
        admin.setPassword(passwordEncoder.encode("12259861"));
        admin.setUserName("mohamed_arous");
        adminRepo.save(admin);
        assertEquals(service.validate(signin) , "Login successful");
    }

    @Test
    void checkSignInAndSignUp3() {
        // test for incorrect password
        SignInDTO signin = new SignInDTO();
        Admin admin = new Admin();
        signin.setUserNameOrEmail("mohamed_arous5");
        signin.setPassword("12259861");
        signin.setRole(Role.ADMIN);
        admin.setFirstName("mohamed1");
        admin.setLastName("arous1");
        admin.setEmail("mohamed.arous940@gmail.com");
        admin.setPassword(passwordEncoder.encode("12315445"));
        admin.setUserName("mohamed_arous5");
        adminRepo.save(admin);
        assertEquals(service.validate(signin) , "Incorrect username or password");
    }

    @Test
    void checkSignInAndSignUp4() {
        // test for non exsiting admin
        SignInDTO signin = new SignInDTO();
        Admin admin = new Admin();
        signin.setUserNameOrEmail("amr_ahmed");
        signin.setPassword("123456789");
        signin.setRole(Role.ADMIN);
        assertEquals(service.validate(signin) , "User does not exist");
    }

    @Test
    void checkSignInAndSignUp5() {
        // test for non exsiting user
        SignInDTO signin = new SignInDTO();
        Admin admin = new Admin();
        signin.setUserNameOrEmail("amr_ahmed");
        signin.setPassword("123456789");
        signin.setRole(Role.USER);
        assertEquals(service.validate(signin) , "User does not exist");
    }

    @Test
    void checkSignInAndSignUp6() {
        // user sign in using Email
        SignInDTO signin = new SignInDTO();
        User signup = new User();
        signin.setUserNameOrEmail("mohamed.arous9402@gmail.com");
        signin.setPassword("arousss2");
        signin.setRole(Role.USER);
        signup.setFirstName("mohamed1");
        signup.setLastName("arous1");
        signup.setEmail("mohamed.arous9402@gmail.com");
        signup.setPassword("arousss2");
        signup.setUserName("mohamed_arous2");
        assertEquals(service.addUser(signup), "Sign up successful");
        assertEquals(service.validate(signin) , "Login successful");
    }


    @Test
    void checkSignInAndSignUp7() {
        // admin sign in using Email
        SignInDTO signin = new SignInDTO();
        Admin admin = new Admin();
        signin.setUserNameOrEmail("mohamed.arous9403@gmail.com");
        signin.setPassword("arousss3");
        signin.setRole(Role.ADMIN);
        admin.setFirstName("mohamed1");
        admin.setLastName("arous1");
        admin.setEmail("mohamed.arous9403@gmail.com");
        admin.setPassword(passwordEncoder.encode("arousss3"));
        admin.setUserName("mohamed_arous3");
        adminRepo.save(admin);
        assertEquals(service.validate(signin) , "Login successful");
    }

    @Test
    void checkSignInAndSignUp8() {
        // user try to sign up using existing Email
        SignInDTO signin = new SignInDTO();
        User signup = new User();
        signup.setFirstName("mohamed1");
        signup.setLastName("arous1");
        signup.setEmail("mohamed.arous9401@gmail.com");
        signup.setPassword("12259861");
        signup.setUserName("mohamed_arous11");
        assertEquals(service.addUser(signup), "credentials already exists");
    }
    @Test
    void checkSignInAndSignUp9() {
        // user try to sign up using existing username
        SignInDTO signin = new SignInDTO();
        User signup = new User();
        signup.setFirstName("mohamed1");
        signup.setLastName("arous1");
        signup.setEmail("mohamed.arous9422@gmail.com");
        signup.setPassword("12259861");
        signup.setUserName("mohamed_arous1");
        assertEquals(service.addUser(signup), "credentials already exists");
    }

}
