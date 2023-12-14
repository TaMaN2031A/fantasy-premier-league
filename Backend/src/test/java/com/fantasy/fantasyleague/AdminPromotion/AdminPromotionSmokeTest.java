package com.fantasy.fantasyleague.AdminPromotion;
import com.fantasy.fantasyleague.Registiration.Service.AdminPromotionService;
import com.fantasy.fantasyleague.Registiration.Service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import static com.fantasy.fantasyleague.Registiration.SharedServices.SharedServices.generateUser;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
@TestPropertySource("classpath:test_local.properties")
@SpringBootTest
public class AdminPromotionSmokeTest {

    private final AdminPromotionService service;
    private final RegistrationService registrationService;
    @Autowired
    AdminPromotionSmokeTest(AdminPromotionService adminPromotionService, RegistrationService registrationService){
        this.service=adminPromotionService;
        this.registrationService = registrationService;
    }
    //smoke Tests
    @Test
    void getAllRequestsSmokeTest(){
        service.getAllUsers(0,10);
    }
    @Test
    void getUsersBySpecificationsSmokeTest(){
        service.searchBySpecification("email","ma@gmail.com",0,10);
    }
    @Test
    void PromoteUserSmokeTest(){
        registrationService.addUser(generateUser("ma@gmail.com","ma","cairo","mohamed","ahmed","123456"));
    }
}