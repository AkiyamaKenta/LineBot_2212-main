package com.example.linebot.value;

import java.time.LocalTime;

public class BJTuple {

    private final String userId;
    private final LocalTime pushAt;
    private final String pushText;

    public BJTuple(
            String userId, LocalTime pushAt, String pushText){
        this.userId = userId;
        this.pushAt = pushAt;
        this.pushText = pushText;
    }

    public String getUserId(){
        return userId;
    }

    public LocalTime getPushAt(){
        return pushAt;
    }

    public String getPushText(){
        return pushText;
    }

}
