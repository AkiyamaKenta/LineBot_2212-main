package com.example.linebot.replier;

import com.example.linebot.AppBJ.SimpleBJ;

import com.example.linebot.BJapp.Blackjack;
import com.example.linebot.replier.Follow;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.MessageEvent;
import com.example.linebot.replier.Parrot;
import com.example.linebot.replier.Intent;
import com.example.linebot.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;


import com.example.linebot.BJapp.Blackjack;

@LineMessageHandler
public class Callback {

    private static final Logger log = LoggerFactory.getLogger(Callback.class);
    private final ReminderService reminderService;

    @Autowired
    public Callback(ReminderService reminderService){
        this.reminderService = reminderService;
    }

    // フォローイベントに対応する
    @EventMapping
    public Message handleFollow(FollowEvent event) {
        // 実際はこのタイミングでフォロワーのユーザIDをデータベースにに格納しておくなど
        Follow follow = new Follow(event);
        return follow.reply();
    }

//    //文章で話しかかられたとき対応する(Parrot)
//    @EventMapping
//    public Message handleMessage(MessageEvent<TextMessageContent> event) {
//        Parrot parrot = new Parrot(event);
//        return parrot.reply();
//    }

    // 文章で話しかけられたとき(テキストメッセージのイベント)に対応する
    @EventMapping
    public Message handleMessage(MessageEvent<TextMessageContent> event) throws IOException, InterruptedException {
        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();
        Intent intent = Intent.whichIntent(text);
        switch (intent){
            //上位モジュール→reminderService呼び出し
            case REMINDER:
                RemindOn reminderOn = reminderService.doReplyOfNewItem(event);
                return reminderOn.reply();
            case BJAPPLICATION:
                Blackjack blackjack = new Blackjack();
                blackjack.start();
            case UNKNOWN:

            default:
                Parrot parrot = new Parrot(event);
                return parrot.reply();
        }
    }
}
