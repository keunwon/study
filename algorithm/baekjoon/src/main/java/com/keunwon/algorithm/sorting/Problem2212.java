package com.keunwon.algorithm.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Problem2212 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());
        final int K = Integer.parseInt(br.readLine());
        final List<Integer> sensors = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sensors.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(sensors);

        if (K >= N) {
            System.out.println(0);
            return;
        }

        List<Integer> distances = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            distances.add(sensors.get(i + 1) - sensors.get(i));
        }
        Collections.sort(distances);

        int sum = 0;
        for (int i = 0; i < distances.size() - K + 1; i++) {
            sum += distances.get(i);
        }
        System.out.println(sum);
    }
}
