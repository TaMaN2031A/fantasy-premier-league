package com.fantasy.fantasyleague.Group.Service;

import com.fantasy.fantasyleague.Group.DTO.*;
import com.fantasy.fantasyleague.Group.Model.GroupFantasy;
import com.fantasy.fantasyleague.Group.Repository.GroupRepository;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserRepository userRepository;

    private static ResponseEntity<Map<String, String>> getMapResponseEntity(UserToGroupAdderDTO userToGroupAdderDTO, GroupFantasy groupFantasy) {
        for (int i = 0; i < groupFantasy.getUsers().size(); i++) {
            if (groupFantasy.getUsers().get(i).getUserName().equals(userToGroupAdderDTO.getUserName()))
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    private static List<UserGroupDTO> pushToList(List<User> toBeIterated) {
        List<UserGroupDTO> list = new ArrayList<>();
        for (int i = 0; i < toBeIterated.size(); i++) {
            list.add(new UserGroupDTO(toBeIterated.get(i)));
        }
        return list;
    }

    private static List<GroupDTO> extracted(List<GroupFantasy> publicGroups) {
        List<GroupDTO> list = new ArrayList<>();
        for (int i = 0; i < publicGroups.size(); i++) {
            list.add(new GroupDTO(publicGroups.get(i)));
        }
        return list;
    }

    public ResponseEntity<Map<String, String>> createGroup(GroupCreatorDTO groupCreatorDTO) {
        Map<String, String> response = new HashMap<>();
        try {
            GroupFantasy groupFantasy = new GroupFantasy();
            User user = userRepository.findByUserName(groupCreatorDTO.getUserName());
            System.out.println(isNull(groupCreatorDTO));
            if (!isNull(groupCreatorDTO))
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            user.getOwnedGroups().add(groupFantasy);
            user.getGroupFantasies().add(groupFantasy);
            groupFantasy.setOwner(user);
            groupFantasy.setName(groupCreatorDTO.getGroupName());
            groupFantasy.setIsPrivate(groupCreatorDTO.getIsPrivate());
            groupFantasy.getUsers().add(user);
            userRepository.save(user);
            GroupFantasy savedGroup = groupRepository.save(groupFantasy);
            if (groupCreatorDTO.getIsPrivate() == 1) {
                response.put("Id", String.valueOf(savedGroup.getGroupID()));
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    private boolean isNull(GroupCreatorDTO groupCreatorDTO) {
        return groupRepository.findByName(groupCreatorDTO.getGroupName()).isEmpty();
    }

    public List<GroupFantasy> getAll() {
        return groupRepository.findAll();
    }

    public ResponseEntity<Map<String, String>> addUserToGroup(UserToGroupAdderDTO userToGroupAdderDTO) {
        try {
            User user = userRepository.findByUserName(userToGroupAdderDTO.getUserName());
            GroupFantasy groupFantasy = groupRepository.getReferenceById(userToGroupAdderDTO.getGroupID());
            ResponseEntity<Map<String, String>> BAD_REQUEST = getMapResponseEntity(userToGroupAdderDTO, groupFantasy);
            if (BAD_REQUEST != null) return BAD_REQUEST;
            user.getGroupFantasies().add(groupFantasy);
            groupFantasy.getUsers().add(user);
            userRepository.save(user);
            groupRepository.save(groupFantasy);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public List<GroupDTO> getGroupsOfUser(String userName) {
        User user = userRepository.findByUserName(userName);
        return extracted(user.getGroupFantasies());
    }

    public ResponseEntity<GroupInfoDTO> getSpecificGroupInfo(String groupID, String userName) {
        try {
        GroupInfoDTO groupInfoDTO = new GroupInfoDTO();
        GroupFantasy groupFantasy = this.groupRepository.getReferenceById(Integer.valueOf(groupID));
        int rank = 0;
        for (; rank < groupFantasy.getUsers().size(); rank++) {
            if (groupFantasy.getUsers().get(rank).getUserName().equals(userName))
                break;
        }
        groupInfoDTO.setRankInGroup(rank + 1);
        groupInfoDTO.setGroupDTOList(pushToList(groupFantasy.getUsers()));
        return new ResponseEntity<>(groupInfoDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public List<GroupDTO> getPublicGroups(String userName) {
        User user = userRepository.findByUserName(userName);
        List<GroupFantasy> publicGroups = groupRepository.findPublicGroupsNotContainingUser(user.getUserName());
        return extracted(publicGroups);
    }
}
