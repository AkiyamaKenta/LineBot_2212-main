package com.example.linebot.value;

import com.example.linebot.replier.Intent;

import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReminderSlot {

    private final LocalTime pushAt;
    private final String pushtext;

    public ReminderSlot(String text){
        // Slotに当たる部分を取り出す正規表現の仕組み(Matcher)を作る
        String regexp = Intent.REMINDER.getRegexp();
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()){
            // textの中で"hh:mmに○○"の正規表現に
            // matchした1 hh 2 mm 3 ○○ を取り出す
            int hours = Integer.parseInt(matcher.group(1));
            int minutes = Integer.parseInt(matcher.group(2));
            this.pushAt = LocalTime.of(hours, minutes);
            this.pushtext = matcher.group(3);
        }else {
            // 正規表現にmatchしない場合、例外処理をthrowする
            throw new IllegalArgumentException("textをスロットに分けられません");
        }
    }

    public LocalTime getPushAt(){
        return pushAt;
    }

    public String getPushtext(){
        return pushtext;
    }

}
