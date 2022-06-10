package com.ch03.java;

import com.ch03.strings.*;

public class StringUtil {

    public static void main(String[] args) {
        char letter1 = StringUtilKt.lastChar("Java");
        char letter2 = StringUtilKt.getLastChar("Java");
        System.out.println(letter1);
        System.out.println(letter2);
    }
}
