package com.fantasy.fantasyleague.Group.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupInfoDTO {
    List<UserGroupDTO> groupDTOList;
    int rankInGroup;
}
