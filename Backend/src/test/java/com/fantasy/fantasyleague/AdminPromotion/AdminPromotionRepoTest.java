package com.fantasy.fantasyleague.AdminPromotion;

import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import com.fantasy.fantasyleague.Registiration.Service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import static com.fantasy.fantasyleague.Registiration.SharedServices.SharedServices.buildSpecification;
import static com.fantasy.fantasyleague.Registiration.SharedServices.SharedServices.generateUser;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
@TestPropertySource("classpath:test_local.properties")
@SpringBootTest
public class AdminPromotionRepoTest {
    private final UserRepository Repo;
    private final RegistrationService registrationService;
    @Autowired
    AdminPromotionRepoTest(UserRepository repo, RegistrationService registrationService){
        Repo = repo;
        this.registrationService = registrationService;
    }

    @Test
    void getAllTest(){
        for(int i=0;i<=5;i++){
           registrationService.addUser(generateUser("ma"+(char)(i + 'a')+"@gmail.com","ma"+(char)(i + 'a'),"cairo","mohamed","ahmed","123456"));
        }
        for(int i=6;i<=11;i++){
            registrationService.addUser(generateUser("ma"+(char)(i + 'a')+"@gmail.com","ma"+(char)(i + 'a'),"cairo","mohamed","ahmed","123456"));
        }
        assertEquals(Repo.findAllUsers(PageRequest.of(0,10)).getTotalElements(),12);
        assertEquals(Repo.findAllUsers(PageRequest.of(0,10)).getNumberOfElements(),10);
        assertEquals(Repo.findAllUsers(PageRequest.of(1,10)).getNumberOfElements(),2);
        assertEquals(Repo.findAllUsers(PageRequest.of(2,10)).getNumberOfElements(),0);
//        returns all users
        assertEquals(12,Repo.findAllUsers(null).getNumberOfElements());
        Repo.deleteAll();
    }
    @Test
    void findAll() {
        for (int i = 0; i <= 5; i++) {
            registrationService.addUser(generateUser("mz" + (char) (i + 'a') + "@gmail.com", "mz" + (char) (i + 'a'), "cairo", "mohamed", "ahmed", "123456"));
        }
        for (int i = 6; i <= 11; i++) {
            registrationService.addUser(generateUser("m" + (char) (i + 'a') + "@gmail.com", "m" + (char) (i + 'a'), "cairo", "mohamed", "ahmed", "123456"));
        }
        Specification<User> EmailSpecification = buildSpecification("email", "mz");
        assertEquals(6,Repo.findAll(EmailSpecification, PageRequest.of(0, 10)).getNumberOfElements());
        assertEquals( 10,Repo.findAll((Specification<User>) null, PageRequest.of(0, 10)).getNumberOfElements());
        Specification<User> NameSpecification = buildSpecification("userName", "mz");
        assertEquals(5,Repo.findAll(NameSpecification, PageRequest.of(0, 5)).getNumberOfElements());
        try {
            Specification<User> SpecificationNotSupported = buildSpecification("us", "mz");
            assertEquals( 5,Repo.findAll(SpecificationNotSupported, PageRequest.of(0, 5)).getNumberOfElements());
            Repo.deleteAll();
        } catch (Exception e) {
            System.out.println("Test Is Working!!");
            Repo.deleteAll();
        }

    }

    @Test
    void findAllSecondTest() {
            for (int i = 0; i <= 5; i++) {
                registrationService.addUser(generateUser("m" + (char) (i + 'a') + "@gmail.com", "mz" + (char) (i + 'a'), "cairo", "mohamed", "ahmed", "123456"));
            }
            Specification<User> EmailSpecification = buildSpecification("userName", "mz");
            assertEquals(6,Repo.findAll(EmailSpecification, PageRequest.of(0, 10)).getNumberOfElements());
            assertEquals(6,Repo.findAll((Specification<User>) null, PageRequest.of(0, 10)).getNumberOfElements());
            Specification<User> NameSpecification = buildSpecification("email", "mb");
            assertEquals(1,Repo.findAll(NameSpecification, PageRequest.of(0, 5)).getNumberOfElements());
            Specification<User> retSpecification = buildSpecification("email", "mb@gmail.com");
            assertEquals("mb@gmail.com",Repo.findAll(retSpecification, PageRequest.of(0, 5)).getContent().get(0).getEmail());
            try {
                Specification<User> SpecificationNotSupported = buildSpecification("us", "mz");
                assertEquals(Repo.findAll(SpecificationNotSupported, PageRequest.of(0, 5)).getNumberOfElements(),5 );
            } catch (Exception e) {
                System.out.println("Test Is Working!!");
            }
            Repo.deleteAll();
    }

    @Test
    void getAllSecondTest() {
        for(int i=0;i<=5;i++){
            registrationService.addUser(generateUser("m"+(char)(i + 'a')+"@gmail.com","ma"+(char)(i + 'a'),"cairo","mohamed","ahmed","123456"));
        }
        assertEquals(6,Repo.findAllUsers(PageRequest.of(0,10)).getTotalElements());
        assertEquals(0,Repo.findAllUsers(PageRequest.of(1,10)).getNumberOfElements());
//        returns all users
        assertEquals(6,Repo.findAllUsers(null).getNumberOfElements());
        Repo.deleteAll();
    }


}
