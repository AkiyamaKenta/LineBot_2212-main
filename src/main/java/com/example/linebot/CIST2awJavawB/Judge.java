package com.example.linebot.CIST2awJavawB;

public class Judge {

    private int psum;
    private int csum;


    public Judge(int psum, int csum) {
        this.psum = psum;
        this.csum = csum;
    }

    public void judgement() {

        System.out.println(psum);
        System.out.println(csum);
        if (psum > 21 && csum > 21) {
            System.out.println("どっちも負け");
        } else if (psum > csum && psum <= 21 || csum > 21) {
            System.out.println("プレイヤーの勝ち");
        } else if (psum == csum) {
            System.out.println("あいこ");
        } else if (csum > psum && csum <= 21 || psum > 21) {
            System.out.println("CPUの勝ち");
        }
    }
}
