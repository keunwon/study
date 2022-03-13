package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem10216 {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] parent;
    static int[][] numbers;

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());

        while (t -- > 0) {
            int n = Integer.parseInt(br.readLine());

            parent = new int[n];
            numbers = new int[n][3];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                numbers[i][0] = Integer.parseInt(st.nextToken());
                numbers[i][1] = Integer.parseInt(st.nextToken());
                numbers[i][2] = Integer.parseInt(st.nextToken());

                parent[i] = i;
            }

            int cnt = n;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int x = numbers[j][0] - numbers[i][0];
                    int y = numbers[j][1] - numbers[i][1];
                    int r = numbers[j][2] + numbers[i][2];

                    if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2)) {
                        if (find(i) != find(j)) {
                            union(j, i);
                            cnt--;
                        }
                    }
                }
            }

            System.out.println(cnt);
        }
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) { return; }

        if (x < y) { parent[y] = x; }
        else { parent[x] = y; }
    }

    static int find(int x) {
        if (parent[x] == x) { return x; }
        return parent[x] = find(parent[x]);
    }
}
