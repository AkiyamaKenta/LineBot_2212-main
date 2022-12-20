package com.example.linebot.CIST2awJavawB;

public class Judge {

    public int psum;
    public int csum;


    public Judge(int psum, int csum) {
        this.psum = psum;
        this.csum = csum;
    }

    public void judgement() {

        System.out.println(psum);
        System.out.println(csum);
        if (psum > 21 && csum > 21) {
            System.out.println("bursted.");
        } else if (psum > csum && psum <= 21 || csum > 21) {
            System.out.println("player won.");
        } else if (psum == csum) {
            System.out.println("draw.");
        } else if (csum > psum && csum <= 21 || psum > 21) {
            System.out.println("computer won.");
        }
    }
}
