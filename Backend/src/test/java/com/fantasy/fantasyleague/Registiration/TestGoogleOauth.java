package com.fantasy.fantasyleague.Registiration;

import com.fantasy.fantasyleague.Registiration.DTO.GoogleDTO;
import com.fantasy.fantasyleague.Registiration.Model.Role;
import com.fantasy.fantasyleague.Registiration.Repository.AdminRepository;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import com.fantasy.fantasyleague.Registiration.Service.RegistrationServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
@TestPropertySource("classpath:test_local.properties")
@SpringBootTest
public class TestGoogleOauth {

    @Autowired
    private RegistrationServiceImpl service;

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void checkGoogleOauth1() {
        // user try to sign up using gmail for first time
        // and after that added to the system

        GoogleDTO obj = new GoogleDTO();
        obj.setEmail("amrahmed@gmail.com");
        obj.setFirstName("Amr");
        obj.setLastName("Ahmed");
        obj.setRole(Role.USER);
        assertEquals(userRepo.findByEmail(obj.getEmail()).isPresent(), false);
        assertEquals(service.validateGoogle(obj) , "Login successful");
        assertEquals(userRepo.findByEmail(obj.getEmail()).isPresent(), true);

    }

    @Test
    void checkGoogleOauth2() {
        // user try to sign up using gmail for first time
        // and after that added to the system
        GoogleDTO obj = new GoogleDTO();
        obj.setEmail("amrahmed@gmail.com");
        obj.setFirstName("Amr");
        obj.setLastName("Ahmed");
        obj.setRole(Role.USER);
        assertEquals(userRepo.findByEmail(obj.getEmail()).isPresent(), true);
        assertEquals(service.validateGoogle(obj) , "Login successful");
    }

    @Test
    void checkGoogleOauth3() {
        // admin try to sign up using gmail for first time
        // and after that added to the system
        GoogleDTO obj = new GoogleDTO();
        obj.setEmail("amrahmed22@gmail.com");
        obj.setFirstName("Amr");
        obj.setLastName("Ahmed");
        obj.setRole(Role.ADMIN);
        assertEquals(adminRepo.findByEmail(obj.getEmail()).isPresent(), false);
        assertEquals(userRepo.findByEmail(obj.getEmail()).isPresent(), false);
        assertEquals(service.validateGoogle(obj) , "User does not exist");
        assertEquals(adminRepo.findByEmail(obj.getEmail()).isPresent(), false);
    }

    @Test
    void checkGoogleOauth4() {
        // user try to sign up using gmail for first time
        // and after that added to the system
        GoogleDTO obj = new GoogleDTO();
        obj.setEmail("amrahmed22@gmail.com");
        obj.setFirstName("Amr");
        obj.setLastName("Ahmed");
        obj.setRole(Role.ADMIN);
        assertEquals(adminRepo.findByEmail(obj.getEmail()).isPresent(), false);
        assertEquals(userRepo.findByEmail(obj.getEmail()).isPresent(), false);

    }
}
