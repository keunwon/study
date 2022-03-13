package com.keunwon.algorithm.bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Problem11723 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final Set<String> result = new HashSet<>();

    public static void main(String[] args) throws IOException {
        final int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            final String command = st.nextToken();

            if ("all".equals(command) || "empty".equals(command)) {
                runCommand(command, "");
            } else {
                runCommand(command, st.nextToken());
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void runCommand(String command, String num) throws IOException {
        switch (command) {
            case "add":
                result.add(num);
                break;
            case "remove":
                result.remove(num);
                break;
            case "check":
                bw.write(result.contains(num) ? "1" : "0");
                bw.newLine();
                break;
            case "toggle":
                if (result.contains(num)) { result.remove(num); }
                else { result.add(num); }
                break;
            case "all":
                result.clear();
                IntStream.rangeClosed(1, 20)
                        .mapToObj(String::valueOf)
                        .forEach(result::add);
                break;
            case "empty":
                result.clear();
                break;
        }
    }
}
