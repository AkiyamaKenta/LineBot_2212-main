package com.example.linebot.value;

public class BJItem {

    private final String userId;
    private final BJSlot slot;

    public BJItem(String userId, BJSlot slot){
        this.userId = userId;
        this.slot = slot;
    }

    public String getUserId(){
        return userId;
    }

    public ReminderSlot getSlot(){
        return slot;
    }

}
