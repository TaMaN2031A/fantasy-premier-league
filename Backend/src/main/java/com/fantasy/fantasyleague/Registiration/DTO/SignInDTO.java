package com.fantasy.fantasyleague.Registiration.DTO;

import com.fantasy.fantasyleague.Registiration.Model.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SignInDTO {

    private String userNameOrEmail;
    private String password;
    private Role role;

}
