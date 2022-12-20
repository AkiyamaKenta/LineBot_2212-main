package com.example.linebot.service;

import com.example.linebot.replier.RemindOn;
import com.example.linebot.value.ReminderItem;
import com.example.linebot.value.ReminderSlot;
import com.example.linebot.value.ReminderTuple;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.linebot.repository.ReminderRepository;
import java.util.List;

@Service
public class ReminderService {

    private final ReminderRepository repository;

    @Autowired
    public ReminderService(ReminderRepository reminderRepository){
        this.repository = reminderRepository;
    }

    //下位モジュール←リスト作成に関するもの
    public List<PushMessage> doPushReminderItems(){
        List<ReminderTuple> ReminderTuples = repository.findPreviousItems();//<1>
        repository.findPreviousItems(); //<1>
        repository.deletePreviousItems();

        List<PushMessage>pushMessages = ReminderTuples.stream()
                .map(tuple -> toPushMessage(tuple))
                .toList(); //<2>

        return pushMessages;
    }
    //下位モジュール←出力に関係するもの
    public PushMessage toPushMessage(ReminderTuple tuple){ //<3>
        String userId = tuple.getUserId();
        String pushText = tuple.getPushText();
        String body = String.format("%s の時間です!", pushText);

        repository.deletePreviousItems();
        return new PushMessage(userId, new TextMessage(body));
    }

    //下位モジュール←入力に関係するもの
    public RemindOn doReplyOfNewItem(MessageEvent<TextMessageContent> event){
        String userId  = event.getSource().getUserId();
        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();
        ReminderSlot slot = new ReminderSlot(text);
        ReminderItem item = new ReminderItem(userId, slot);

        repository.insert(item);

        return new RemindOn(text);
    }

}
