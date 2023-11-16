package com.fantasy.fantasyleague.Registiration.Service;

import com.fantasy.fantasyleague.Registiration.DTO.SignInDTO;
import com.fantasy.fantasyleague.Registiration.DTO.SignUpDTO;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

public interface AdminService {

    String validateAdmin(SignInDTO signInDTO);
    ResponseEntity<String> updatePassword(JsonNode PasswordUpdateInfo);
    ResponseEntity<String> ForgetPassword(JsonNode emailDetails);


}
