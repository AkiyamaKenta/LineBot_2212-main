package com.example.linebot.service;

import com.example.linebot.replier.BJOn;
import com.example.linebot.repository.BJRepository;
import com.example.linebot.value.BJItem;
import com.example.linebot.value.BJSlot;
import com.example.linebot.value.BJTuple;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BJService {

    private final BJRepository repository;

    @Autowired
    public BJService(BJRepository bjRepository) {
        this.repository = bjRepository;
    }

    //下位モジュール←リスト作成に関するもの
    public List<PushMessage> doPushReminderItems(){
        List<BJTuple> BJTuples = repository.findPreviousItems();//<1>
        repository.findPreviousItems(); //<1>
        repository.deletePreviousItems();

        List<PushMessage>pushMessages = BJTuples.stream()
                .map(tuple -> toPushMessage(tuple))
                .toList(); //<2>

        //List<PushMessage> pushMessages = new ArrayList<>();   <2>
        //for (ReminderTuple tuple : ReminderTuples) {
        //    PushMessage pushMessage = toPushMessage(tuple);
        //    pushMessages.add(pushMessage);
        //}


        return pushMessages;
    }
    //下位モジュール←出力に関係するもの
    public PushMessage toPushMessage(BJTuple tuple){ //<3>
        String userId = tuple.getUserId();
        String pushText = tuple.getPushText();
        String body = String.format("%s の時間です!", pushText);

        repository.deletePreviousItems();
        return new PushMessage(userId, new TextMessage(body));
    }

    //下位モジュール←入力に関係するもの
    public BJOn doReplyOfNewItem(MessageEvent<TextMessageContent> event){
        String userId  = event.getSource().getUserId();
        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();
        BJSlot slot = new BJSlot(text);
        BJItem item = new BJItem(userId, slot);

        repository.insert(item);

        return new BJOn(text);
    }

}