package com.example.groupwallet;

import java.io.Serializable;

public class Group implements Serializable {

    String groupId;
    String groupName;
    String groupDesc;
//    String[] members;

    //TODO: Add members to group and uncomment constructor and getter

    public Group() {

    }

    public Group(String groupId, String groupName, String groupDesc) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupDesc = groupDesc;
    }

//    public Group(String groupId, String groupName, String groupDesc, String[] members) {
//        this.groupId = groupId;
//        this.groupName = groupName;
//        this.groupDesc = groupDesc;
//        this.members = members;
//    }

    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

//    public String[] getMembers() {
//        return members;
//    }
}
