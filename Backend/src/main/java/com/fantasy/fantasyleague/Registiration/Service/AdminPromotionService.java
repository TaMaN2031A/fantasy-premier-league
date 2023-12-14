package com.fantasy.fantasyleague.Registiration.Service;

import com.fantasy.fantasyleague.Registiration.DTO.AdminPromotionDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;


public interface AdminPromotionService {
    ResponseEntity<Page<AdminPromotionDTO>> getAllUsers(int page, int size);
    <T> ResponseEntity<Page<AdminPromotionDTO>> searchBySpecification(String specificationType, T value, int page, int size);
    ResponseEntity<String>PromoteUser(String UserNameOrEmail);
}
