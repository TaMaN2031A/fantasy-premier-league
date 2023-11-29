package com.fantasy.fantasyleague.Registiration;

import com.fantasy.fantasyleague.Registiration.Model.Admin;
import com.fantasy.fantasyleague.Registiration.Model.Person;
import com.fantasy.fantasyleague.Registiration.Model.Role;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Repository.AdminRepository;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import com.fantasy.fantasyleague.Registiration.Service.RegistrationService;
import com.fantasy.fantasyleague.Registiration.Service.RegistrationServiceImpl;
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
public class TestUtilities {
    @Autowired
    private RegistrationServiceImpl service;

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User generateUser(String email,
                          String userName,
                          String region,
                          String firstName,
                          String lastName,
                          String password) {
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(passwordEncoder.encode(password));
        user.setRegion(region);
        user.setUserName(userName);
        return user;
    }

    public Admin generateAdmin(String email,
                                String userName,
                                String region,
                                String firstName,
                                String lastName,
                                String password) {
        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setFirstName(firstName);
        admin.setLastName(lastName);
        admin.setPassword(passwordEncoder.encode(password));
        admin.setRegion(region);
        admin.setUserName(userName);
        return admin;
    }

    @Test
    void checkPasswordFn1() {
        // check password function for equal
        String password = "heLlo_woRld12&$";
        String encoded = passwordEncoder.encode(password);
        assertTrue(service.checkPassword(password, encoded));
    }

    @Test
    void checkPasswordFn2() {
        // check password function for not equal
        String password = "heLlo_woRld12&$";
        String encoded = passwordEncoder.encode(password);
        password = "heLlo2&$";
        assertFalse(service.checkPassword(password, encoded));
    }

    @Test
    void checkPasswordFn3() {
        // check password function for not equal in differnce between uppercase and lower case only
        String password = "heLlo_woRld12&$";
        String encoded = passwordEncoder.encode(password);
        password = "hello_world12&$";
        assertFalse(service.checkPassword(password, encoded));
    }

    @Test
    void checkPasswordFn4() {
        // check password function for empty password
        String password = "";
        String encoded = passwordEncoder.encode(password);
        assertTrue(service.checkPassword(password, encoded));
    }

    @Test
    void checkFindEntity1() {
        // test user exist in database using email or username
        User user = generateUser(
                "amro@gmail.com",
                "amro",
                "EGY",
                "Amr",
                "Ahmed",
                "12345"
        );
        userRepo.save(user);
        Person person = service.findEntity("amro@gmail.com", "amro", Role.USER);
        assertNotNull(person);
        assertEquals(person.getRegion(), "EGY");
        assertEquals(person.getFirstName(), "Amr");
    }

    @Test
    void checkFindEntity2() {
        // test admin exist in database using email or username
        Admin admin = generateAdmin(
                "kamal@gmail.com",
                "Kamal123",
                "Spain",
                "Kamal",
                "Mohamed",
                "12345"
        );
        adminRepo.save(admin);
        Person person = service.findEntity("kamal@gmail.com", "Kamal123", Role.ADMIN);
        Person person2 = service.findEntity("kamal@gmail.com", "Kamal123", Role.USER);
        assertNull(person2);
        assertNotNull(person);
        assertEquals(person.getRegion(), "Spain");
        assertEquals(person.getLastName(), "Mohamed");
        assertNotEquals(person.getPassword(), "12345");
    }

    @Test
    void checkFindEntity3() {
        // test when used to find by username or email (used mainly in sign in)
        Person person = service.findEntity("kamal@gmail.com", "kamal@gmail.com", Role.ADMIN);
        assertNotNull(person);
        assertEquals(person.getRegion(), "Spain");
        assertEquals(person.getLastName(), "Mohamed");
        assertNotEquals(person.getPassword(), "12345");
    }

}
