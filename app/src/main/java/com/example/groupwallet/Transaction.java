package com.example.groupwallet;

public class Transaction {

    String transId;
    String groupId;
    String transName;
    String paidBy;
    Double amount;
    String date;

    public  Transaction() {

    }

    public Transaction(String transId, String groupId, String transName, String paidBy, Double amount, String date) {
        this.transId = transId;
        this.groupId = groupId;
        this.transName = transName;
        this.paidBy = paidBy;
        this.amount = amount;
        this.date = date;
    }

    public String getTransId() {
        return transId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getTransName() {
        return transName;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
}
