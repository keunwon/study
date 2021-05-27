package com.keunwon.chapter08;

public class RunThreads {

    public static void main(String[] args) {
        RunnableImpl runnable = new RunnableImpl();
        ThreadExtends threadExtends = new ThreadExtends();

        new Thread(runnable).start();
        threadExtends.start();
    }
}
