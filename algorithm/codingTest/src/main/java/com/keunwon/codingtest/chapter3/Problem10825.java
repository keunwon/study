package com.keunwon.codingtest.chapter3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Problem10825 {
    static int N;
    static List<Elem> elems = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        input();

        elems.stream().sorted().forEach(elem -> System.out.println(elem.name));
    }

    private static void input() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = parseInt(br.readLine());

            for (int i = 0; i < N; i++) {
                elems.add(new Elem(new StringTokenizer(br.readLine())));
            }
        }
    }


    static class Elem implements Comparable<Elem> {
        String name;
        int korean, english, math;

        public Elem(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

        public Elem(StringTokenizer st) {
            this(st.nextToken(), parseInt(st.nextToken()), parseInt(st.nextToken()), parseInt(st.nextToken()));
        }

        @Override
        public int compareTo(Elem o) {
            if (korean != o.korean) { return o.korean - korean; }
            if (english != o.english) { return english - o.english; }
            if (math != o.math) { return o.math - math; }
            return name.compareTo(o.name);
        }
    }
}
