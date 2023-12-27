package com.fantasy.fantasyleague.Group.DTO;

import com.fantasy.fantasyleague.Group.Model.GroupFantasy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {
    String groupName;
    int groupID;
    String ownerName;
    int noParticipants;
    double avgPoints;
    public GroupDTO(GroupFantasy groupFantasy){
        groupName = groupFantasy.getName();
        groupID = groupFantasy.getGroupID();
        ownerName = groupFantasy.getOwner().getUserName();
        noParticipants = groupFantasy.getUsers().size();
        double sum = 0;
        for(int i = 0; i < groupFantasy.getUsers().size(); i++){
            sum += groupFantasy.getUsers().get(i).getPoints();
        }
        avgPoints = sum/noParticipants;
    }
}
