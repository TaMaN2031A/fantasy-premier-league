package com.fantasy.fantasyleague.Group.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupCreatorDTO {
    private String groupName;
    private int isPrivate;
    private String userName;
}
