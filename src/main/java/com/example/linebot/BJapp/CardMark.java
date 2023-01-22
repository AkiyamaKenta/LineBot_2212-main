package com.example.linebot.BJapp;

public class CardMark {
    public static final CardMark SPADE = new CardMark("スペード");
    public static final CardMark HEART = new CardMark("ハート");
    public static final CardMark DIAMOND = new CardMark("ダイヤ");
    public static final CardMark CLUB = new CardMark("クラブ");

    public static final CardMark[] MARKS = {SPADE, HEART, DIAMOND, CLUB};

    private final String mark;

    private CardMark(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }
}


