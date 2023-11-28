package com.fantasy.fantasyleague.Registiration.Service;

import com.fantasy.fantasyleague.Registiration.DTO.GoogleDTO;
import com.fantasy.fantasyleague.Registiration.DTO.SignInDTO;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;


public interface RegistrationService {

    String addUser(User user);

    String validate(SignInDTO signInDTO);

    String validateGoogle(GoogleDTO googleDTO);

    ResponseEntity<String> updatePassword(JsonNode PasswordUpdateInfo);

    ResponseEntity<String> ForgetPassword(JsonNode emailDetails);

}
