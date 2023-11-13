package com.fantasy.fantasyleague.Group;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GroupFantasy {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int groupID;

    private String name;
    private String code;
    private int isPrivate;
    private int ownerID;

    public GroupFantasy(String name, String code, int isPrivate, int ownerID) {
        this.name = name;
        this.code = code;
        this.isPrivate = isPrivate;
        this.ownerID = ownerID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int isPrivate() {
        return isPrivate;
    }

    public void setPrivate(int aPrivate) {
        isPrivate = aPrivate;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }
}
