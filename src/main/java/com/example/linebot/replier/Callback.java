package com.example.linebot.replier;

//import com.example.linebot.service.CovidGovService;
import com.example.linebot.service.ReminderService;
//import com.example.linebot.service.StoreSalesPredictionService;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


@LineMessageHandler
public class Callback {

    private static final Logger log = LoggerFactory.getLogger(Callback.class);
    private final ReminderService reminderService;
//    private final CovidGovService covidGovService;
//    private final StoreSalesPredictionService storeSalesPredictionService;

    @Autowired
//    public Callback(ReminderService reminderService, CovidGovService covidGovService, StoreSalesPredictionService storeSalesPredictionService){
//        this.reminderService = reminderService;
//        this.covidGovService = covidGovService;
//        this.storeSalesPredictionService = storeSalesPredictionService;
//    }

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
//            case COVID_TOTAL:
//                CovidReport covidReport = covidGovService.doReplyWithCovid(event);
//                return covidReport.reply();
//            case COVID_INCREASE:
//                CovidCalcIncreaseRate covidCalcIncreaseRate = covidGovService.doReplyWithCovid2(event);
//                return covidCalcIncreaseRate.reply();
//            case SSPSRESULT:
//                SSPReport sspReport = storeSalesPredictionService.doReplyWithURL(event);
//                return sspReport.reply();
            case UNKNOWN:

            default:
                Parrot parrot = new Parrot(event);
                return parrot.reply();
        }
    }
}
