package com.keunwon.chapter08;

public class Sleep extends Thread {

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
            System.out.println("run~~~");
        } catch (InterruptedException e) {
            System.out.println("Somebody stopped me T T");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Sleep s = new Sleep();
        s.start();

        try {
            int cnt = 0;
            while (cnt < 5) {
                s.join(1000);
                cnt++;
                System.out.format("%d second waited\n", cnt);
            }

            if (s.isAlive()) {
                s.interrupt();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
