package com.keunwon.algorithm.programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

public class Lesson250136 {
    private final int[][] moves = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private int[][] land;
    private int[][] groupLand;
    private Map<Integer, Integer> countMap = new HashMap<>();

    public int solution(int[][] land) {
        this.land = land;
        this.groupLand = new int[land.length][land[0].length];

        groupingLand();

        var answer = 0;
        for (var i = 0; i < groupLand[0].length; i++) {
            var teams = new HashSet<Integer>();

            for (var j = 0; j < groupLand.length; j++) {
                var id = groupLand[j][i];
                if (id != 0) teams.add(id);
            }

            var sum = 0;
            for (var team : teams) {
                sum += countMap.get(team);
            }
            answer = Math.max(answer, sum);
        }
        return answer;
    }

    private void groupingLand() {
        var visited = new boolean[land.length][land[0].length];
        var id = 1;

        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[i].length; j++) {
                if (land[i][j] == 1 && !visited[i][j]) {
                    bfs(visited, new int[]{i, j}, id);
                    ++id;
                }
            }
        }
    }

    private void bfs(boolean[][] visited, int[] start, int id) {
        var queue = new LinkedList<int[]>();
        var positions = new ArrayList<int[]>();

        queue.offer(start);
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            var cur = queue.poll();
            positions.add(cur);

            for (var move : moves) {
                var nr = cur[0] + move[0];
                var nc = cur[1] + move[1];

                if (nr < 0 || nc < 0 || nr >= land.length || nc == land[0].length) continue;
                if (visited[nr][nc] || land[nr][nc] == 0) continue;

                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
            }
        }

        for (var arr : positions) {
            groupLand[arr[0]][arr[1]] = id;
        }
        countMap.put(id, positions.size());
    }
}
