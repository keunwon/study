package com.keunwon.algorithm.bfs;

import java.util.LinkedList;
import java.util.Scanner;

public class Problem12886 {
    private static Stone start;

    public static void main(String[] args) {
        input();
        System.out.println(bfs() ? 1 : 0);
    }

    private static boolean bfs() {
        var queue = new LinkedList<Stone>();
        var visited = new boolean[1501][1501];

        if ((start.a + start.b + start.c) % 3 != 0) {
            return false;
        }

        queue.offer(start);

        while (!queue.isEmpty()) {
            var stone = queue.poll();

            if (stone.a == stone.b && stone.b == stone.c) {
                return true;
            }

            if (stone.a != stone.b) {
                var plus = Math.min(stone.a, stone.b) * 2;
                var minus = Math.max(stone.a, stone.b) - Math.min(stone.a, stone.b);

                if (!visited[plus][minus]) {
                    queue.offer(new Stone(plus, minus, stone.c));
                    visited[plus][minus] = visited[minus][plus] = true;
                }
            }

            if (stone.a != stone.c) {
                var plus = Math.min(stone.a, stone.c) * 2;
                var minus = Math.max(stone.a, stone.c) - Math.min(stone.a, stone.c);

                if (!visited[plus][minus]) {
                    queue.offer(new Stone(plus, stone.b, minus));
                    visited[plus][minus] = visited[minus][plus] = true;
                }
            }

            if (stone.b != stone.c) {
                var plus = Math.min(stone.b, stone.c) * 2;
                var minus = Math.max(stone.b, stone.c) - Math.min(stone.b, stone.c);

                if (!visited[plus][minus]) {
                    queue.offer(new Stone(stone.a, plus, minus));
                    visited[plus][minus] = visited[minus][plus] = true;
                }
            }
        }
        return false;
    }

    private static void input() {
        Scanner sc = new Scanner(System.in);
        start = new Stone(sc.nextInt(), sc.nextInt(), sc.nextInt());
    }

    private static class Stone {
        int a;
        int b;
        int c;

        Stone(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}
