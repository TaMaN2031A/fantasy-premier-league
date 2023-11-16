package com.fantasy.fantasyleague.Registiration.DTO;

import com.fantasy.fantasyleague.Registiration.Model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDTO {

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String region;

    public User mapToUser(String password) {
        return new User(
                this.userName,
                this.email,
                this.region,
                this.firstName,
                this.lastName,
                password,
                ""
        );
    }

}
