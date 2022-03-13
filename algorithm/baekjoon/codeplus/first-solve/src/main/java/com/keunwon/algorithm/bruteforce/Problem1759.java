package com.keunwon.algorithm.bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem1759 {
    public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static final char[] vowels = {'a', 'e', 'i', 'o', 'u'};

    public static char[] words;
    public static char[] selected;
    public static boolean[] visited;
    public static int L, C;

    public static void main(String[] args) throws IOException {
        input();
        dfs(0, 0);
        bw.flush();
    }

    private static void dfs(int start, int depth) throws IOException {
        if (L == depth) {
            if (!hasVowel() || !hasTwoConsonant()) {
                return;
            }

            for (char c : selected) {
                bw.write(c);
            }
            bw.newLine();
            return;
        }

        for (int i = start; i < C; i++) {
            if (visited[i]) { continue; }

            visited[i] = true;
            selected[depth] = words[i];
            dfs(i, depth + 1);
            visited[i] = false;
        }
    }

    private static boolean hasTwoConsonant() {
        int count = 0;
        for (char c : selected) {
            for (char vowel : vowels) {
                if (c == vowel) {
                    count++;
                }
            }
        }
        return selected.length - count >= 2;
    }

    private static boolean hasVowel() {
        for (char c : selected) {
            for (char vowel : vowels) {
                if (c == vowel) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        selected = new char[L];
        visited = new boolean[C];
        words = br.readLine().replaceAll(" ", "").toCharArray();

        Arrays.sort(words);
    }
}
