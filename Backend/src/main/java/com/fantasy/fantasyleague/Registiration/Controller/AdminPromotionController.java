package com.fantasy.fantasyleague.Registiration.Controller;

import com.fantasy.fantasyleague.Registiration.DTO.AdminPromotionDTO;
import com.fantasy.fantasyleague.Registiration.Service.AdminPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/AdminPromotion")
public class AdminPromotionController {

    private final
    AdminPromotionService adminPromotionService;

    @Autowired
    public AdminPromotionController(AdminPromotionService adminPromotionService) {
        this.adminPromotionService = adminPromotionService;
    }


    @GetMapping("/GetUsers")
    public ResponseEntity<Page<AdminPromotionDTO>> getAllUsers(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return adminPromotionService.getAllUsers(pageNo, pageSize);
    }
    @GetMapping("/searchBy")
    public <T> ResponseEntity<Page<AdminPromotionDTO>> searchUsers(
            @RequestParam String specificationType, @RequestParam T value, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return adminPromotionService.searchBySpecification(specificationType, value, page, size);
    }
    @PutMapping ("/promote")
    public ResponseEntity<String> promoteUser(@RequestParam String UserNameOrEmail){
        return adminPromotionService.PromoteUser(UserNameOrEmail);
    }
}