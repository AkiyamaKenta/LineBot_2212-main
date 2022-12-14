package com.example.linebot.CIST2awJavawB;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cardList;

    public Deck(){
        String[] marks = new String[]{"ダイヤ", "スペード", "ハート", "クローバー"};
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

    public void printCardList(){
        for (int i = 0; i < cardList.size(); i++){
            Card card = cardList.get(i);
            card.print();
        }
    }

    public Card drawCard(int a){
        if(a < 0 || a > cardList.size()){
            throw new IndexOutOfBoundsException();
        }
        Card card = cardList.get(a);
        cardList.remove(a);
        card.print();
        return card;
    }

    public int getLength(){
        return cardList.size();
    }

}
