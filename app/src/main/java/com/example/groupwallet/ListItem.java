package com.example.groupwallet;

public class ListItem {

    private String name;
    private String desc;

    public ListItem(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
