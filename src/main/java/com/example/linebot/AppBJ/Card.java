package com.example.linebot.AppBJ;

public class Card {

    private String mark;
    private int number;

    public Card(String mark, int number){
        this.mark = mark;
        this.number = number;
    }

    public void print(){
        System.out.println(mark + " " + number);
    }

    public int getNumber(){
        return number;
    }

}
