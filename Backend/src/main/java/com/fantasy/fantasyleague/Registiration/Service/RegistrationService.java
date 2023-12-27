package com.fantasy.fantasyleague.Registiration.Service;

import com.fantasy.fantasyleague.Registiration.DTO.GoogleDTO;
import com.fantasy.fantasyleague.Registiration.DTO.SignInDTO;
import com.fantasy.fantasyleague.Registiration.Model.Response;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

public interface RegistrationService {

    ResponseEntity<String> addUser(User user);

    ResponseEntity<String> validate(SignInDTO signInDTO);

    ResponseEntity<String> validateGoogle(GoogleDTO googleDTO);


    String updatePassword(JsonNode PasswordUpdateInfo);

    String ForgetPassword(JsonNode emailDetails);

}