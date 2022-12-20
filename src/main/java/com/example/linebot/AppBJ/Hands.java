package com.example.linebot.AppBJ;

import java.util.ArrayList;

public class Hands {

    private ArrayList<Card> hands = new ArrayList<>();

    public void add(Card card){
        hands.add(card);
    }

    public int count(){
        int sum = 0;
        for(int i = 0;i<hands.size();i++){
            Card card = hands.get(i);
            sum = sum + card.getNumber();
        }
        return sum;
    }

}
