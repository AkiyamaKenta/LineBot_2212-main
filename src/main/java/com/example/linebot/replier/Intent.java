package com.example.linebot.replier;

import java.util.EnumSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Intent {

    // メッセージの正規表現パターンに対応するやり取りの定義
    REMINDER("^(\\d{1,2}):(\\d{1,2})に(.{1,32})$"),
    COVID_TOTAL("^(.*)の感染者数"),
    COVID_INCREASE("^(.*)の増加率"),
    SSPSRESULT("storesales"),
    UNKNOWN(".+");


    private final String regexp;

    private Intent(String regexp) {
        this.regexp = regexp;
    }

    // メッセージからやり取りの状態を判断
    public static Intent whichIntent(String text) {
        // 全てのIntent(REMINDER, UNKNOWN)を取得
        EnumSet<Intent> set = EnumSet.allOf(Intent.class);
        // 引数text が、 REMINDER, UNKNOWN のパターンに当てはまるかチェック
        // あてはまった方を戻り値とする
        for (Intent intent : set) {
            if (Pattern.matches(intent.regexp, text)) {
                return intent;
            }
        }

        return UNKNOWN;
    }

    public String getRegexp(){
        return regexp;
    }

}
