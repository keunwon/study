package com.keunwon.chapter08;

public class Contributor extends Thread {
    private Contribution myContribution;
    private String myName;

    public Contributor(Contribution myContribution, String myName) {
        this.myContribution = myContribution;
        this.myName = myName;
    }

    @Override
    public void run() {
        for (int loop = 0; loop < 1000; loop++) {
            myContribution.donoate();
        }

        System.out.format("%s total=%d\n", myName, myContribution.getTotal());
    }
}
