package com.example.linebot;

import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.example.linebot.service.ReminderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.message.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

@RestController//<1>
public class MessagePush {

    private static final Logger log = LoggerFactory.getLogger((MessagePush.class));

    private String userid = "U7d703802fc572fd27857e569c6cec052";

    private final LineMessagingClient messagingClient;
    private final ReminderService reminderService;

    @Autowired
    public MessagePush(LineMessagingClient lineMessagingClient, ReminderService reminderService){
        this.messagingClient = lineMessagingClient;
        this.reminderService = reminderService;
    }

    // テスト
    @GetMapping("test")
    public String hello(HttpServletRequest request) {
        return "Get from " + request.getRequestURL();
    }

    //時報をプッシュする
    @GetMapping("timetone")//<2>
//    @Scheduled(cron = "0 */1 * * * *", zone = "Asia/Tokyo")//<1>
    public String pushTimeTone() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("a K:mm");//<3>
        String text = dtf.format(LocalDateTime.now());
        try {
            PushMessage pMsg = new PushMessage(userid, new TextMessage(text));//<4>
            BotApiResponse resp = messagingClient.pushMessage(pMsg).get();
            log.info("Sent Messages: {}", resp);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return text;//<5>
    }

    @Scheduled(cron = "0 */1 * * * *", zone = "Asia/Tokyo")
    public void pushReminder(){
        try {
            List<PushMessage> messages = reminderService.doPushReminderItems(); //<1>
            for (PushMessage message : messages) {
                BotApiResponse resp = messagingClient.pushMessage(message).get(); //<2>
                log.info("Sent messages: {}", resp);
            }
        }catch (InterruptedException | ExecutionException e){
            throw new RuntimeException(e);
        }

    }

}
