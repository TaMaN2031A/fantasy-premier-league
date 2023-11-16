package com.fantasy.fantasyleague.Registiration.Service;

import com.fantasy.fantasyleague.Registiration.DTO.SignInDTO;
import com.fantasy.fantasyleague.Registiration.DTO.SignUpDTO;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface UserService {
    String addUser(SignUpDTO signUpDTO);
    boolean validateUser(SignInDTO signInDTO);
    ResponseEntity<String> updatePassword(JsonNode PasswordUpdateInfo);
    ResponseEntity<String> ForgetPassword(JsonNode emailDetails);

}
