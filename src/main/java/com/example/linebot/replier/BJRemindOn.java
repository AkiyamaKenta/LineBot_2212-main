package com.example.linebot.replier;

import com.example.linebot.AppBJ.SimpleBJ;

import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

public class BJRemindOn implements Replier{

    @Override
    public Message reply(){
        return new TextMessage(SimpleBJ.main);
    }

}
