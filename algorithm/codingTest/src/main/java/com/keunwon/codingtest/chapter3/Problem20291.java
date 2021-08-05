package com.keunwon.codingtest.chapter3;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Problem20291 {
    static int N;
    static Map<String, Integer> map = new TreeMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < N; i++) {
            final String key = sc.nextLine().split("\\.")[1];

            if (!map.containsKey(key)) {
                map.put(key, 1);
            } else {
                Integer value = map.get(key);
                map.put(key, ++value);
            }
        }

        map.forEach((k, v) -> System.out.println(k + " " + v));
    }
}
