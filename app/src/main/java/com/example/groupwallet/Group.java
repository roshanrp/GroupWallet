package com.example.groupwallet;

import java.io.Serializable;
import java.util.List;

public class Group implements Serializable {

    String groupId;
    String groupName;
    String groupDesc;
    List<Participant> members;

    public Group() {

    }

    public Group(String groupId, String groupName, String groupDesc, List<Participant> members) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupDesc = groupDesc;
        this.members = members;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public List<Participant> getMembers() {
        return members;
    }

}
