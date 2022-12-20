package com.example.linebot.CIST2awJavawB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck2 {

    private ArrayList<Card> cardList;

    public Deck2(){
        String[] marks = new String[]{"Diamond", "Spade", "Heart", "Clover"};
        ArrayList<Card> cardList = new ArrayList<>();
        for (int i = 0; i < marks.length; i++){
            String mark = marks[i];
            for (int j = 1; j < 14; j++){
                Card card = new Card(mark, j);
                cardList.add(card);
            }
        }

        Collections.shuffle(cardList);
        this.cardList = cardList;

    }


    public Card drawCard(){
        Card card = cardList.get(0);
        cardList.remove(0);
        card.print();
        return card;
    }

    public int getLength(){
        return cardList.size();
    }

}
