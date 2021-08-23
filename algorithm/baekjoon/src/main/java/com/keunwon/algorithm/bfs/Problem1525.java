package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem1525 {
    static final String answer = "123456780";
    static final int[][] moves = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

    static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                sb.append(st.nextToken());
            }
        }

        map.put(sb.toString(), 0);
        System.out.println(bfs(sb.toString()));
    }

    static int bfs(String input) {
        Queue<String> queue = new LinkedList<>();
        queue.add(input);

        while (!queue.isEmpty()) {
            String nums = queue.poll();
            int x = nums.indexOf("0") / 3;
            int y = nums.indexOf("0") % 3;

            if (nums.equals(answer)) {
                return map.get(nums);
            }

            for (int[] move : moves) {
                int nx = x + move[0];
                int ny = y + move[1];

                if (!validMap(nx, ny)) { continue; }

                int nIdx = nx * 3 + ny;
                char value = nums.charAt(nIdx);
                String replaceNums = nums.replace(value, 'C');
                replaceNums = replaceNums.replace('0', value);
                replaceNums = replaceNums.replace('C', '0');

                if (!map.containsKey(replaceNums)) {
                    queue.add(replaceNums);
                    map.put(replaceNums, map.get(nums) + 1);
                }
            }
        }
        return -1;
    }

    static boolean validMap(int x, int y) {
        return x >= 0 && x < 3 && y >= 0 && y < 3;
    }
}
