package com.keunwon.algorithm.sorting;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Problem1427 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = br.readLine();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            list.add(input.charAt(i) - '0');
        }

        list.sort((o1, o2) -> o2 - o1);

        String result = list.stream().map(String::valueOf).collect(Collectors.joining());
        System.out.println(result);
    }
}
