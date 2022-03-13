package com.keunwon.algorithm.datastructures;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem10824 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine());

        Long num1 = Long.parseLong(st.nextToken() + st.nextToken());
        Long num2 = Long.parseLong(st.nextToken() + st.nextToken());

        System.out.println(num1 + num2);
    }
}
