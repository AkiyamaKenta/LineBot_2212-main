package com.example.linebot.CIST2awJavawB;

import java.util.Random;
import java.util.Scanner;

public class SimpleBJ {
    public static void main(String[] args) {

        Random r = new Random();
        Deck deck = new Deck();

        Scanner s1 = new Scanner(System.in);

        Player pl1 = new Player();
        Player com = new Player();

        for(int i = 0;i < 2;i++){

            System.out.print("配列番号を入力してください=");
            int index = s1.nextInt();
            try {
                pl1.add(deck.drawCard(index));
            }catch (IndexOutOfBoundsException e){
                System.out.println("0~" + deck.getLength() + "までで入力してください");
                e.printStackTrace();
                System.exit(1);
            }
        }

        com.add(deck.drawCard(r.nextInt(deck.getLength())));
        com.add(deck.drawCard(r.nextInt(deck.getLength())));

        Judge judge = new Judge(pl1.count(), com.count());
        judge.judgement();

    }
}
