package com.fantasy.fantasyleague.Registiration.DTO;

import com.fantasy.fantasyleague.Registiration.Model.Role;
import com.fantasy.fantasyleague.Registiration.Model.User;
import lombok.Data;
import net.bytebuddy.utility.RandomString;

@Data
public class GoogleDTO {

    private String email;
    private final String region = "International";
    private String firstName;
    private String lastName;
    private Role role;

    public User userMapper(String encodedPassword) {
        User user = new User();
        user.setEmail(this.email);
        user.setUserName(this.email);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setRegion(this.region);
        user.setPassword(encodedPassword);
        return user;
    }
}
