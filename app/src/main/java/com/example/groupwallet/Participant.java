package com.example.groupwallet;

public class Participant {

    String groupId;
    String memberId;
    String memberName;

    public  Participant() {

    }

    public Participant(String groupId, String memberId, String memberName) {
        this.groupId = groupId;
        this.memberId = memberId;
        this.memberName = memberName;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }
}
