package com.fantasy.fantasyleague.Group.Service;

import com.fantasy.fantasyleague.Group.DTO.GroupCreatorDTO;
import com.fantasy.fantasyleague.Group.DTO.GroupDTO;
import com.fantasy.fantasyleague.Group.DTO.GroupInfoDTO;
import com.fantasy.fantasyleague.Group.DTO.UserToGroupAdderDTO;
import com.fantasy.fantasyleague.Group.Model.GroupFantasy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public interface GroupService {
    ResponseEntity<Map<String, String>> createGroup(GroupCreatorDTO groupCreatorDTO);
    List<GroupFantasy> getAll();
    ResponseEntity<Map<String, String>> addUserToGroup(UserToGroupAdderDTO userToGroupAdderDTO);
    List<GroupDTO> getGroupsOfUser(String userName);
    ResponseEntity<GroupInfoDTO> getSpecificGroupInfo(String groupID, String userName);
    List<GroupDTO> getPublicGroups(String userName);
}
