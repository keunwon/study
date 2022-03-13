package com.keunwon.algorithm.datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Problem11656 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        List<String> list = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            list.add(input.substring(i));
        }

        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s).append(System.lineSeparator());
        }
        System.out.print(sb.toString());
    }
}
