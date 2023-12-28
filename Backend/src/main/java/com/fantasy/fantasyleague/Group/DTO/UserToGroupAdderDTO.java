package com.fantasy.fantasyleague.Group.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserToGroupAdderDTO {
    private String userName;
    private int groupID;
}
