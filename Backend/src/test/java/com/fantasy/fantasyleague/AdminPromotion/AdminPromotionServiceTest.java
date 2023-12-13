package com.fantasy.fantasyleague.AdminPromotion;

import com.fantasy.fantasyleague.Registiration.DTO.AdminPromotionDTO;
import com.fantasy.fantasyleague.Registiration.Model.Admin;
import com.fantasy.fantasyleague.Registiration.Model.Mail;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Repository.AdminRepository;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import com.fantasy.fantasyleague.Registiration.Service.AdminPromotionServiceImplementation;
import com.fantasy.fantasyleague.Registiration.Service.MailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminPromotionServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private MailService mailService;

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminPromotionServiceImplementation adminPromotionService;

    @Test
    public void testGetAllUsers_Success() {
        // Mocking userRepository.findAll
        Pageable pageable = PageRequest.of(0, 10);
        when(userRepository.findAll(pageable)).thenReturn(createMockUserPage());

        // Testing adminPromotionService.getAllUsers
        ResponseEntity<Page<AdminPromotionDTO>> response = adminPromotionService.getAllUsers(0, 10);

        // Verifying the result
        assertEquals(3, Objects.requireNonNull(response.getBody()).getTotalElements());
        assertEquals(200, response.getStatusCode().value());

        // Verifying that userRepository.findAll is called once with the expected parameters
        verify(userRepository, times(1)).findAll(pageable);
    }

    @Test
    public void testGetAllUsers_EmptyPage() {
        // Mocking userRepository.findAll
        Pageable pageable = PageRequest.of(0, 10);
        when(userRepository.findAll(pageable)).thenReturn(createMockEmptyUserPage());

        // Testing adminPromotionService.getAllUsers
        ResponseEntity<Page<AdminPromotionDTO>> response = adminPromotionService.getAllUsers(0, 10);

        // Verifying the result for an empty page
        assertEquals(204, response.getStatusCode().value());
        assertNull(response.getBody());

        // Verifying that userRepository.findAll is called once with the expected parameters
        verify(userRepository, times(1)).findAll(pageable);
    }

    @Test
    public void testGetAllUsers_Exception() {
        // Mocking userRepository.findAll to throw an exception
        Pageable pageable = PageRequest.of(0, 10);
        when(userRepository.findAll(pageable)).thenThrow(new RuntimeException("Test exception"));

        // Testing adminPromotionService.getAllUsers
        ResponseEntity<Page<AdminPromotionDTO>> response = adminPromotionService.getAllUsers(0, 10);

        // Verifying the result for an exception
        assertEquals(500, response.getStatusCode().value());

        // Verifying that userRepository.findAll is called once with the expected parameters
        verify(userRepository, times(1)).findAll(pageable);
    }

    @Test
    public void testSearchBySpecification_Success() {
        // Mocking userRepository.findAll
        Pageable pageable = PageRequest.of(0, 10);
        when(userRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(createMockUserPage());

        // Testing adminPromotionService.searchBySpecification
        ResponseEntity<Page<AdminPromotionDTO>> response = adminPromotionService.searchBySpecification("email", "user1@g",0, 10);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("user1@example.com", Objects.requireNonNull(response.getBody()).getContent().get(0).getEmail());
        assertEquals("user1", Objects.requireNonNull(response.getBody()).getContent().get(0).getUserName());
    }
    @Test
    public void testSearchBySpecificationFailEmpty() {
        // Mocking userRepository.findAll
        Pageable pageable = PageRequest.of(0, 10);
        when(userRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(createMockEmptyUserPage());
        // Testing adminPromotionService.searchBySpecification
        ResponseEntity<Page<AdminPromotionDTO>> response = adminPromotionService.searchBySpecification("email", "user1@g",0, 10);
        assertEquals(204, response.getStatusCode().value());
    }
    @Test
    public void testSearchBySpecificationFailException() {
        // Mocking userRepository.findAll
        Pageable pageable = PageRequest.of(0, 10);
        when(userRepository.findAll(any(Specification.class), eq(pageable))).thenThrow(new RuntimeException("Test exception"));
        // Testing adminPromotionService.searchBySpecification
        ResponseEntity<Page<AdminPromotionDTO>> response = adminPromotionService.searchBySpecification("email", "user1@g",0, 10);
        assertEquals(500, response.getStatusCode().value());
    }

    @Test
    public void testPromoteUser_UserFound_AdminNotFound_Success() {
        // Arrange
        String userNameOrEmail = "testUser";
        User user = new User();
        user.setEmail("user@example.com");
        user.setUserName("testUser");

        // Mocking findEntity method to return a user and no admin
        when(userRepository.findByEmailOrUserName(userNameOrEmail, userNameOrEmail)).thenReturn(user);
        when(adminRepository.findByEmailOrUserName(userNameOrEmail, userNameOrEmail)).thenReturn(null);

        // Mocking userRepository.delete and adminRepository.save
        doNothing().when(userRepository).delete(user);
        when(adminRepository.save(any(Admin.class))).thenReturn(new Admin(user));

        // Mocking mailService.sendEmail
        when(mailService.sendEmail(any(Mail.class))).thenReturn(ResponseEntity.ok("Email sent successfully"));

        // Act
        ResponseEntity<String> response = adminPromotionService.PromoteUser(userNameOrEmail);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Promoted successfully", response.getBody());

        // Verify that the expected methods were called
        verify(userRepository, times(1)).findByEmailOrUserName(userNameOrEmail, userNameOrEmail);
        verify(adminRepository, times(1)).findByEmailOrUserName(userNameOrEmail, userNameOrEmail);
        verify(userRepository, times(1)).delete(user);
        verify(adminRepository, times(1)).save(any(Admin.class));
        verify(mailService, times(1)).sendEmail(any(Mail.class));
    }
    @Test
    public void testPromoteUser_UserFound_AdminNotFound_Failure() {
        // Arrange
        String userNameOrEmail = "testUser";
        User user = new User();
        user.setEmail("user@example.com");
        user.setUserName("testUser");

        // Mocking findEntity method to return a user and no admin
        when(userRepository.findByEmailOrUserName(userNameOrEmail, userNameOrEmail)).thenReturn(user);
        when(adminRepository.findByEmailOrUserName(userNameOrEmail, userNameOrEmail)).thenReturn(null);

        // Mocking userRepository.delete and adminRepository.save
        doNothing().when(userRepository).delete(user);
        when(adminRepository.save(any(Admin.class))).thenReturn(new Admin(user));

        // Mocking mailService.sendEmail
        when(mailService.sendEmail(any(Mail.class))).thenThrow(new RuntimeException("Test exception"));

        // Act
        ResponseEntity<String> response = adminPromotionService.PromoteUser(userNameOrEmail);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("promotion failed", response.getBody());

        // Verify that the expected methods were called
        verify(userRepository, times(1)).findByEmailOrUserName(userNameOrEmail, userNameOrEmail);
        verify(adminRepository, times(1)).findByEmailOrUserName(userNameOrEmail, userNameOrEmail);
        verify(userRepository, times(1)).delete(user);
        verify(adminRepository, times(1)).save(any(Admin.class));
        verify(mailService, times(1)).sendEmail(any(Mail.class));
    }

    @Test
    public void testPromoteUser_UserNotFound_AdminFound_BadRequest() {
        // Arrange
        String userNameOrEmail = "testAdmin";
        Admin admin = new Admin();
        admin.setEmail("admin@example.com");
        admin.setUserName("testAdmin");

        // Mocking findEntity method to return no user and an admin
        when(userRepository.findByEmailOrUserName(userNameOrEmail, userNameOrEmail)).thenReturn(null);
        when(adminRepository.findByEmailOrUserName(userNameOrEmail, userNameOrEmail)).thenReturn(admin);

        // Act
        ResponseEntity<String> response = adminPromotionService.PromoteUser(userNameOrEmail);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User already promoted", response.getBody());

        // Verify that the expected methods were called
        verify(userRepository, times(1)).findByEmailOrUserName(userNameOrEmail, userNameOrEmail);
        verify(adminRepository, times(1)).findByEmailOrUserName(userNameOrEmail, userNameOrEmail);
        verify(userRepository, never()).delete(any(User.class));
        verify(adminRepository, never()).save(any(Admin.class));
        verify(mailService, never()).sendEmail(any(Mail.class));
    }

    @Test
    public void testPromoteUser_UserNotFound_AdminNotFound_UserNotFound_BadRequest() {
        // Arrange
        String userNameOrEmail = "nonexistentUser";

        // Mocking findEntity method to return no user and no admin
        when(userRepository.findByEmailOrUserName(userNameOrEmail, userNameOrEmail)).thenReturn(null);
        when(adminRepository.findByEmailOrUserName(userNameOrEmail, userNameOrEmail)).thenReturn(null);

        // Act
        ResponseEntity<String> response = adminPromotionService.PromoteUser(userNameOrEmail);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User not found", response.getBody());

        // Verify that the expected methods were called
        verify(userRepository, times(1)).findByEmailOrUserName(userNameOrEmail, userNameOrEmail);
        verify(adminRepository, times(1)).findByEmailOrUserName(userNameOrEmail, userNameOrEmail);
        verify(userRepository, never()).delete(any(User.class));
        verify(adminRepository, never()).save(any(Admin.class));
        verify(mailService, never()).sendEmail(any(Mail.class));
    }

    private Page<User> createMockUserPage() {
        User user1 = new User();
        user1.setEmail("user1@example.com");
        user1.setUserName("user1");

        User user2 = new User();
        user2.setEmail("user2@example.com");
        user2.setUserName("user2");

        User user3 = new User();
        user3.setUserName("user3");
        user3.setEmail("user3@example.com");
        return new PageImpl<>(Arrays.asList(user1,user2, user3));
    }
    private Page<User> createMockEmptyUserPage() {
              return new PageImpl<>(List.of());
    }
}
