package algorithm.programmers;

import java.util.LinkedList;

public class Lesson87694 {
    private int[][] map = new int[102][102];
    private boolean[][] visited = new boolean[102][102];
    private int[][] moves = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for (var data : rectangle) {
            for (var x = data[0] * 2; x <= data[2] * 2; x++) {
                for (var y = data[1] * 2; y <= data[3] * 2; y++) {
                    map[x][y] = 1;
                }
            }
        }

        for (var data : rectangle) {
            for (var x = data[0] * 2 + 1; x < data[2] * 2; x++) {
                for (var y = data[1] * 2 + 1; y < data[3] * 2; y++) {
                    map[x][y] = 0;
                }
            }
        }

        var queue = new LinkedList<Node>();
        visited[characterX * 2][characterY * 2] = true;
        queue.offer(new Node(characterX * 2, characterY * 2, 0));

        while (!queue.isEmpty()) {
            var cur = queue.poll();

            if (cur.x == itemX * 2 && cur.y == itemY * 2) return cur.distance / 2;

            for (var move : moves) {
                var nx = cur.x + move[0];
                var ny = cur.y + move[1];

                if (nx < 0 || ny < 0 || nx >= map.length || ny >= map[0].length) continue;
                if (visited[nx][ny] || map[nx][ny] == 0) continue;

                visited[nx][ny] = true;
                queue.offer(new Node(nx, ny, cur.distance + 1));
            }
        }
        return 0;
    }

    private static class Node {
        int x;
        int y;
        int distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
