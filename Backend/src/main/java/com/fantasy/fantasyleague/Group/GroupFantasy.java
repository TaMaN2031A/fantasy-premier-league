package com.fantasy.fantasyleague.Group;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
@Getter
@Entity
@NoArgsConstructor

public class GroupFantasy {
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int groupID;
    @Setter
    private String name;
    @Setter
    private String code;
    @Setter
    private int isPrivate;
    @Setter
    private int ownerID;

    public GroupFantasy(String name, String code, int isPrivate, int ownerID) {
        this.name = name;
        this.code = code;
        this.isPrivate = isPrivate;
        this.ownerID = ownerID;
    }

}
