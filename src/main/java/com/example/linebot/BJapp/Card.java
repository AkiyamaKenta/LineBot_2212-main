package com.example.linebot.BJapp;

public class Card {
    private final CardNumber number;
    private final CardMark mark;

    public Card(CardNumber number, CardMark mark) {
        this.number = number;
        this.mark = mark;
    }

    public CardNumber getNumber() {
        return number;
    }

    public CardMark getMark() {
        return mark;
    }
}

