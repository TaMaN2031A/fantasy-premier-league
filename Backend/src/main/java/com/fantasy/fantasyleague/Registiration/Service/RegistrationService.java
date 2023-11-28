package com.fantasy.fantasyleague.Registiration.Service;

import com.fantasy.fantasyleague.Registiration.DTO.GoogleDTO;
import com.fantasy.fantasyleague.Registiration.DTO.SignInDTO;
import com.fantasy.fantasyleague.Registiration.Model.User;

public interface RegistrationService {

    String addUser(User user);

    String validate(SignInDTO signInDTO);

    String validateGoogle(GoogleDTO googleDTO);
}
