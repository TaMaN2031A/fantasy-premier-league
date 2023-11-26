package com.fantasy.fantasyleague.Registiration.Service;

import com.fantasy.fantasyleague.Registiration.DTO.SignInDTO;
import com.fantasy.fantasyleague.Registiration.DTO.SignUpDTO;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import org.springframework.stereotype.Service;

public interface UserService {

    String addUser(SignUpDTO signUpDTO);

    String validateUser(SignInDTO signInDTO);

}
