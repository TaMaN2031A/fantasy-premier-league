package com.fantasy.fantasyleague.Group.Controller;

import com.fantasy.fantasyleague.Group.DTO.*;
import com.fantasy.fantasyleague.Group.Model.GroupFantasy;
import com.fantasy.fantasyleague.Group.Service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupService groupService;

    @GetMapping("/getUserGroups/{userName}")
    public List<GroupDTO> getUserGroups(@PathVariable String userName) {
        System.out.println(userName);
        return groupService.getGroupsOfUser(userName);
    }

    @GetMapping("/getPublicGroups/{userName}")
    public List<GroupDTO> getPublicGroups(@PathVariable String userName) {
        System.out.println(userName);
        return groupService.getPublicGroups(userName);
    }

    @GetMapping("/getSpecificGroupInfo/{groupID}/{userName}")
    public List<UserGroupDTO> getSpecificGroupInfo(@PathVariable String groupID,
                                                   @PathVariable String userName) {
        return groupService.getSpecificGroupInfo(groupID, userName);
    }

    @GetMapping("/getAll")
    public List<GroupFantasy> getAll() {
        return groupService.getAll();
    }

    @PostMapping("/createGroup")
    public ResponseEntity<Map<String, String>> createGroup(@RequestBody GroupCreatorDTO groupCreatorDTO) {
        return groupService.createGroup(groupCreatorDTO);
    }

    @PostMapping("/addUserToGroup")
    public ResponseEntity<Map<String, String>> addUserToGroup(@RequestBody UserToGroupAdderDTO userToGroupAdderDTO) {
        return groupService.addUserToGroup(userToGroupAdderDTO);
    }


}
