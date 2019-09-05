package com.keunwon.java8.chap11;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;

public class Utils {

    private static Random random = new Random(0);
    private static DecimalFormat formatter = new DecimalFormat("#.##",new DecimalFormatSymbols(Locale.US));

    public static void delay() {
        int delay  = 1000;

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static double format(double number) {
        synchronized (formatter) {
            return new Double(formatter.format(number));
        }
    }



}
