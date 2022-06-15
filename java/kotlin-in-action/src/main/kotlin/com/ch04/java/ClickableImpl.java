package com.ch04.java;

import com.ch04.*;

public class ClickableImpl implements Clickable {

    @Override
    public void click() {
        System.out.println("click");
    }

    @Override
    public void showOff() {
        Clickable.DefaultImpls.showOff(this);
    }
}