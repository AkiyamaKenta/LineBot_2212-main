package com.example.linebot.CIST2awJavawB;

import java.util.ArrayList;
import java.util.Collections;

public class CollectionsTest {

    public static void main(String[] args) {

        ArrayList<Integer> intList = new ArrayList<>();

        intList.add(8);
        intList.add(9);
        intList.add(5);
        intList.add(6);

        int n1 = intList.get(2);
        System.out.println("n1=" + n1);

        Collections.shuffle(intList);

        int n2 = intList.get(2);
        System.out.println("n2=" + n2);

    }
}
