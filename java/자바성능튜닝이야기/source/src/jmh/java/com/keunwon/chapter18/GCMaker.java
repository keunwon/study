package com.keunwon.chapter18;

import java.util.ArrayList;
import java.util.List;

public class GCMaker {

    public static void main(String[] args) throws InterruptedException {
        GCMaker maker = new GCMaker();

        for (int i = 0; i < 120; i++) {
            maker.makeObject();
            Thread.sleep(1000);
            System.out.println(".");
        }
    }

    private void makeObject() {
        Integer[] intArr = new Integer[1024000];
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 1024; i++) {
            intArr[i] = i;
            list.add(i);
        }
    }
}
