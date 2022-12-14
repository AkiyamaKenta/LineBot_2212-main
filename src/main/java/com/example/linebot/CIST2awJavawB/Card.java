package com.example.linebot.CIST2awJavawB;

public class Card {

    private String mark;
    private int number;

    public Card(String mark, int number){
        this.mark = mark;
        this.number = number;
    }

    public void print(){
        System.out.println(mark + "の" + number);
    }

    public int getNumber(){
        return number;
    }

}
