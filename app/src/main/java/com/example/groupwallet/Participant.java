package com.example.groupwallet;

public class Participant {

    String groupId;
    String memberId;
    String memberName;
    Double amountPaid;
    Double share;

    public  Participant() {

    }

    public Participant(String groupId, String memberId, String memberName, Double amountPaid, Double share) {
        this.groupId = groupId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.amountPaid = amountPaid;
        this.share = share;
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

    public Double getAmountPaid() {
        return amountPaid;
    }

    public Double getShare() {
        return share;
    }
}
