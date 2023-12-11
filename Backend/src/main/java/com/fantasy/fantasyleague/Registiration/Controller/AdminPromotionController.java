package com.fantasy.fantasyleague.Registiration.Controller;

import com.fantasy.fantasyleague.Registiration.DTO.AdminPromotionDTO;
import com.fantasy.fantasyleague.Registiration.Service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/AdminPromotion")
public class AdminPromotionController {

    final
    RegistrationService registrationService;
    @Autowired
    public AdminPromotionController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/users")
    public Page<AdminPromotionDTO> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable Page = PageRequest.of(page, 10);
        return registrationService.GetAllUsers(Page);
    }
    @GetMapping("/search")
    public <T> Page<AdminPromotionDTO> searchUsers(
            @RequestParam String specificationType,
            @RequestParam T value,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return registrationService.searchBySpecification(specificationType, value, page, size);
    }

}