package com.ch03.java;

import com.ch03.*;

import java.util.*;

public class JoinStringMain {

    public static void main(String[] args) {
        String join1 = StringFunctions.joinToString(Arrays.asList(1, 2, 3));
        String join2 = CollectionJoinKt.joinToString(Arrays.asList(1, 2, 3));

        System.out.println(join1);
        System.out.println(join2);
    }
}
