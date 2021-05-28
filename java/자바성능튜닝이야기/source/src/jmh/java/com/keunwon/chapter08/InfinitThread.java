package com.keunwon.chapter08;

public class InfinitThread extends Thread {

    private int value = Integer.MAX_VALUE;
    private boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            value++;
            if (value == Integer.MAX_VALUE) {
                value = Integer.MIN_VALUE;
                System.out.println("MAX_VALUE reached !!!");
            }

            try {
                Thread.sleep(0, 1);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
