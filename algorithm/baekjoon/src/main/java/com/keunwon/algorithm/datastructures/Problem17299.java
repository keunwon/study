package com.keunwon.algorithm.datastructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem17299 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());
        final Position[] positions = new Position[N];
        final Map<Integer, Integer> map = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            positions[i] = new Position(i, num);
            map.compute(num, (key, value) -> value == null ? 1 : value + 1);
        }

        Stack<Position> stack = new Stack<>();
        int[] result = new int[N];
        Arrays.fill(result, - 1);

        for (int i = 0; i < N - 1; i++) {
            Position now = positions[i];
            Position next = positions[i + 1];

            stack.add(now);

            while (!stack.isEmpty() && map.get(now.num) < map.get(next.num)) {
                Position p = stack.pop();
                result[p.idx] = next.num;

                if (!stack.isEmpty()) {
                    now = stack.peek();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            sb.append(i).append(" ");
        }

        System.out.println(sb.toString());
    }

    static class Position {
        int idx;
        int num;

        public Position(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }
}
