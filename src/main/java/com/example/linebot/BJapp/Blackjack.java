package com.example.linebot.BJapp;

import java.util.Scanner;

public class Blackjack {

    private final Deck deck;
    private final Hand playerHand;
    private final Hand dealerHand;

    public Blackjack() {
        this.deck = new Deck();
        this.playerHand = new Hand();
        this.dealerHand = new Hand();
    }

    public void start() {
        deck.shuffle();
        dealCards();
        play();
        showResults();
    }

    private void dealCards() {
        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());
        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());

        System.out.println(playerHand);
        System.out.println(dealerHand);
    }

    private void play() {
        while (playerWantsToHit()) {
            playerHand.addCard(deck.drawCard());
            System.out.println(playerHand);
        }
        while (dealerShouldHit()) {
            dealerHand.addCard(deck.drawCard());
            System.out.println(dealerHand);
        }
    }

    private boolean playerWantsToHit() {
        while (true) {
            // code to ask player if they want to hit
            Scanner scanner = new Scanner(System.in);
            System.out.println("Do you want to hit? (yes or no)");
            String input = scanner.nextLine();
            return input.equalsIgnoreCase("yes");
        }
    }

    private boolean dealerShouldHit() {
        return handValue(dealerHand) < 17;
    }

    private int handValue(Hand hand) {
        int value = 0;
        for (Card card : hand.getCards()) {
            value += card.getNumber().getNumber();
        }
        return value;
    }

    private void showResults() {
        System.out.println("Player : " + handValue(playerHand));
        System.out.println("Dealer : " + handValue(dealerHand));

        if (handValue(playerHand) > 21){
            if (handValue(dealerHand) > 21){
                System.out.println("Draw.");
            }
            if (handValue(dealerHand) <= 21){
                System.out.println("Lose.");
            }
        }
        else {
            if (handValue(playerHand) > handValue(dealerHand)){
                System.out.println("Win.");
            }
            else if (handValue(playerHand) == handValue(dealerHand)){
                System.out.println("Draw.");
            }
            else {
                System.out.println("Lose.");
            }
        }
    }


}

