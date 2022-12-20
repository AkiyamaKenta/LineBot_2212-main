package com.example.linebot.AppBJ;

import java.util.Scanner;

public class SimpleBJ {

    public static void main(String[] args) {

        Deck deck = new Deck();

        Hands plr1 = new Hands();
        Hands com1 = new Hands();

        Scanner s1 = new Scanner(System.in);


        System.out.println("Game:BlackJack Start");

        // 初期手札
        System.out.println("Player's starting hands");
        plr1.add(deck.drawCard());
        plr1.add(deck.drawCard());
        System.out.println("Computer's starting hands");
        com1.add(deck.drawCard());
        com1.add(deck.drawCard());

        //追加ドロー
        System.out.println("Do you want to draw one more card?--[1:yes/2:no]");
        int moreFlag = s1.nextInt();
        switch (moreFlag) {
            case 1:
                System.out.println("Player's additional card");
                plr1.add(deck.drawCard());
            case 2:
                break;
            default:
        }

        System.out.println("Computer's additional card");


        if (com1.count() < 17){
            com1.add(deck.drawCard());
        }

        //勝敗判定
        Judge judge = new Judge(plr1.count(), com1.count());
        judge.judgement();

    }

}
