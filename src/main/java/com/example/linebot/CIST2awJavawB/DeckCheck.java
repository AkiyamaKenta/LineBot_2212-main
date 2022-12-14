package com.example.linebot.CIST2awJavawB;

import java.util.Scanner;

public class DeckCheck {

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.printCardList();

        System.out.println("Input 配列番号");
        Scanner s = new Scanner(System.in);
        int index = s.nextInt();

        try {
            deck.drawCard(index);
        }catch (IndexOutOfBoundsException e1){
            System.out.println("Input 0-51.");
            e1.printStackTrace();
        }
    }

}
