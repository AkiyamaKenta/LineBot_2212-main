package com.example.linebot.BJapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class Deck {
    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        for (CardMark mark : CardMark.MARKS) {
            for (CardNumber number : CardNumber.NUMBERS) {
                this.cards.add(new Card(number, mark));
            }
        }
    }


    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            System.out.println("No card in the Deck.");
            throw new NoSuchElementException();
        }
        return cards.remove(0);
    }
}
