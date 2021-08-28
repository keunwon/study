package com.keunwon.algorithm.datastructures;

import java.util.Scanner;

public class Problem11655 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            char encrypt = (char) (c + 13);

            if (isUpperCase(c)) {
                if (isUpperCase(encrypt)) {
                    sb.append(encrypt);
                } else {
                    char ch = (char) (encrypt - 'Z' + 64);
                    sb.append(ch);
                }
            } else if (isLowerCase(c)) {
                if (isLowerCase(encrypt)) {
                    sb.append(encrypt);
                } else {
                    char ch = (char) (encrypt - 'z' + 96);
                    sb.append(ch);
                }
            } else {
                sb.append(c);
            }
        }

        System.out.println(sb.toString());
    }

    static boolean isUpperCase(char c) {
        return 65 <= c && c <= 90;
    }

    static boolean isLowerCase(char c) {
        return 97 <= c && c <= 122;
    }
}
