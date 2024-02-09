package com.fantasy.fantasyleague.Group.DTO;

import com.fantasy.fantasyleague.Registiration.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupDTO {
    private String firstName;
    private String lastName;
    private String userName;
    private String region;
    private int overallPoints;
    public UserGroupDTO(User user){
        firstName = user.getFirstName();
        lastName = user.getLastName();
        userName = user.getUserName();
        region = user.getRegion();
        overallPoints = user.getPoints();
    }
}
