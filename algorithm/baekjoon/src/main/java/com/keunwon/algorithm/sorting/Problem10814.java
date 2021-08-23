package com.keunwon.algorithm.sorting;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem10814 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        List<Person> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer sb = new StringTokenizer(br.readLine());
            list.add(new Person(Integer.parseInt(sb.nextToken()), sb.nextToken()));
        }

        StringBuilder sb = new StringBuilder();
        list.stream()
                .sorted()
                .forEach(p -> sb.append(p.age).append(" ").append(p.name).append("\n"));

        bw.write(sb.toString());
        bw.flush();
    }

    static class Person implements Comparable<Person> {
        int age;
        String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(Person o) {
            return age - o.age;
        }
    }
}
