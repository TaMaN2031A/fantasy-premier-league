package com.fantasy.fantasyleague.Registiration.Service;

import com.fantasy.fantasyleague.Registiration.DTO.AdminPromotionDTO;
import com.fantasy.fantasyleague.Registiration.DTO.GoogleDTO;
import com.fantasy.fantasyleague.Registiration.DTO.SignInDTO;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface RegistrationService {

    String addUser(User user);

    String validate(SignInDTO signInDTO);

    String validateGoogle(GoogleDTO googleDTO);


    String updatePassword(JsonNode PasswordUpdateInfo);

    String ForgetPassword(JsonNode emailDetails);

    Page<AdminPromotionDTO> GetAllUsers(Pageable pageable);

    <T> Page<AdminPromotionDTO> searchBySpecification(String specificationType, T value, int page, int size);

}