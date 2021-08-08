package com.keunwon.algorithm.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem1543 {

    //ababababa
    //aba
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String doc = br.readLine();
        String word = br.readLine();

        int count = 0;
        for (int i = 0; i < doc.length() - word.length() + 1; i++) {
            String w = doc.substring(i, i + word.length());

            if (w.equals(word)) {
                i += word.length() - 1;
                count++;
            }
        }

        System.out.println(count);
        br.close();
    }
}
