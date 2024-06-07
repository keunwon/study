package com.keunwon.algorithm.programmers;

import java.util.LinkedList;

public class Lesson1844 {
    private final int[][] moves = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int solution(int[][] maps) {
        var queue = new LinkedList<Node>();
        var visited = new boolean[maps.length][maps[0].length];
        queue.offer(new Node(0, 0, 1));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            var cur = queue.poll();

            if (cur.r == maps.length - 1 && cur.c == maps[0].length - 1) return cur.dist;

            for (int[] move : moves) {
                var nr = cur.r + move[0];
                var nc = cur.c + move[1];

                if (nr < 0 || nc < 0 || nr >= maps.length || nc >= maps[0].length) continue;
                if (visited[nr][nc] || maps[nr][nc] == 0) continue;

                queue.offer(new Node(nr, nc, cur.dist + 1));
                visited[nr][nc] = true;
            }
        }
        return -1;
    }

    private static class Node {
        int r;
        int c;
        int dist;

        public Node(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }
}
