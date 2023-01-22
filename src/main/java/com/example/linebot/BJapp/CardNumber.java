package com.example.linebot.BJapp;

public class CardNumber {

    public static final CardNumber ACE = new CardNumber(1);
    public static final CardNumber TWO = new CardNumber(2);
    public static final CardNumber THREE = new CardNumber(3);
    public static final CardNumber FOUR = new CardNumber(4);
    public static final CardNumber FIVE = new CardNumber(5);
    public static final CardNumber SIX = new CardNumber(6);
    public static final CardNumber SEVEN = new CardNumber(7);
    public static final CardNumber EIGHT = new CardNumber(8);
    public static final CardNumber NINE = new CardNumber(9);
    public static final CardNumber TEN = new CardNumber(10);
    public static final CardNumber JACK = new CardNumber(10);
    public static final CardNumber QUEEN = new CardNumber(10);
    public static final CardNumber KING = new CardNumber(10);

    public static final CardNumber[] NUMBERS = {ACE, TWO, THREE, FOUR,
            FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING};

    private final int number;

    private CardNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
