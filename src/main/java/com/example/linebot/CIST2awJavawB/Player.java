package com.example.linebot.CIST2awJavawB;

import java.util.ArrayList;

public class Player {

    private ArrayList<Card> tehuda = new ArrayList<>();

    public void add(Card card){
        tehuda.add(card);
    }

    public int count(){
        int sum = 0;
        for(int i = 0;i<tehuda.size();i++){
            Card card = tehuda.get(i);
            sum = sum + card.getNumber();
        }
        return sum;
    }

}
