package com.keunwon.algorithm.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Problem9205 {

    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t -- > 0) {
            final int n = sc.nextInt();
            final List<Position> positions = new ArrayList<>();

            for (int i = 0; i < n + 2; i++) {
                positions.add(new Position(sc.nextInt(), sc.nextInt()));
            }

            final Position start = positions.get(0);
            final Position end = positions.get(n + 1);
            final Queue<Position> queue = new LinkedList<>();
            final boolean[] visited = new boolean[n + 2];
            boolean isSuccess = false;

            queue.add(start);
            visited[0] = true;

            while (!queue.isEmpty()) {
                Position p = queue.poll();

                if (p.x == end.x && p.y == end.y) {
                    isSuccess = true;
                    break;
                }

                for (int i = 1; i < positions.size(); i++) {
                    Position np = positions.get(i);

                    if (Math.abs(p.x - np.x) + Math.abs(p.y - np.y) <= 1000 && !visited[i]) {
                        queue.add(np);
                        visited[i] = true;
                    }
                }
            }

            if (isSuccess) {
                System.out.println("happy");
            } else {
                System.out.println("sad");
            }
        }
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
