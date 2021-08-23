package com.keunwon.algorithm.sorting;

import java.io.*;
import java.util.*;

public class Problem1181 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        Set<String> words = new HashSet<>();

        for (int i = 0; i < N; i++) {
            words.add(br.readLine());
        }

        StringBuilder sb = new StringBuilder();
        words.stream()
                .sorted((o1, o2) -> {
                    if (o1.length() == o2.length()) {
                        return o1.compareTo(o2);
                    }
                    return o1.length() - o2.length();
                })
                .forEach(s -> sb.append(s).append("\n"));

        bw.write(sb.toString());
        bw.flush();
    }
}
