package com.fantasy.fantasyleague.Registiration.Service;
import com.fantasy.fantasyleague.Registiration.DTO.AdminPromotionDTO;
import com.fantasy.fantasyleague.Registiration.Model.*;
import com.fantasy.fantasyleague.Registiration.Repository.AdminRepository;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

import static com.fantasy.fantasyleague.Registiration.SharedServices.SharedServices.buildSpecification;
import static com.fantasy.fantasyleague.Registiration.SharedServices.SharedServices.findEntity;

@Service
public class AdminPromotionServiceImplementation implements AdminPromotionService {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final MailService mailService;

    @Autowired
    public AdminPromotionServiceImplementation(UserRepository userRepository, AdminRepository adminRepository, MailService mailService) {
        this.userRepository=userRepository;
        this.adminRepository=adminRepository;
        this.mailService = mailService;
    }

    public ResponseEntity<Page<AdminPromotionDTO>> getAllUsers(int pageNo, int pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            Page<User> usersPage = userRepository.findAll(pageable);

            if (usersPage.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            Page<AdminPromotionDTO> adminPromotionDTOPage = new PageImpl<>(
                    usersPage.getContent().stream()
                            .map(user -> new AdminPromotionDTO(user.getUserName(), user.getEmail()))
                            .toList(),
                    pageable, usersPage.getTotalElements()
            );
            return ResponseEntity.ok(adminPromotionDTOPage);
        } catch (Exception e) {
            // Handle exceptions and return an error response
            return ResponseEntity.status(500).body(null);
        }
    }
    public <T> ResponseEntity<Page<AdminPromotionDTO>> searchBySpecification(String specificationType, T value, int page, int size) {
        try {
            Specification<User> specification = buildSpecification(specificationType, value);
            Pageable pageable = PageRequest.of(page, size);
            Page<User> userPage = userRepository.findAll(specification, pageable);
            // Check if the page is empty or any additional conditions based on your requirements
            if (userPage.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            // Convert User Page to AdminPromotionDTO Page
            Page<AdminPromotionDTO> adminPromotionDTOPage = userPage.map(user ->
                    new AdminPromotionDTO(user.getUserName(),user.getEmail()));
            return ResponseEntity.ok(adminPromotionDTOPage);
        } catch (Exception e) {
            // Handle exceptions and return an error response
            return ResponseEntity.status(500).body(null);
        }
    }
    @Override
    @Transactional
    public ResponseEntity<String> PromoteUser(String UserNameOrEmail) {
        try {
            Person user = findEntity(adminRepository,userRepository,UserNameOrEmail,UserNameOrEmail, Role.USER);
            Person admin = findEntity(adminRepository,userRepository,UserNameOrEmail,UserNameOrEmail, Role.ADMIN);
            if (user==null && admin == null)
                return ResponseEntity.badRequest().body("User not found");
            if (user == null) {
                return ResponseEntity.badRequest().body("User already promoted");
            }
            userRepository.delete((User) user);
            adminRepository.save(new Admin((User) user));
            mailService.sendEmail(new Mail(user.getEmail(),user.getUserName(),"Promotion To Admin","Congratulations,You have been promoted to admin"));
            return ResponseEntity.ok("Promoted successfully");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("promotion failed");
        }
    }

}
