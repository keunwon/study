package com.keunwon.algorithm.datastructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Problem17298 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());
        final Position[] positions = new Position[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            positions[i] = new Position(i, Integer.parseInt(st.nextToken()));
        }

        int[] result = new int[N];
        Arrays.fill(result, -1);

        Stack<Position> stack = new Stack<>();
        for (int i = 0; i < positions.length - 1; i++) {
            stack.push(positions[i]);

            while (!stack.isEmpty() && stack.peek().num < positions[i + 1].num) {
                Position p = stack.pop();
                result[p.idx] = positions[i + 1].num;
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
