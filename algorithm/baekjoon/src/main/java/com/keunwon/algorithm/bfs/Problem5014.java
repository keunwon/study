package com.keunwon.algorithm.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem5014 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int F = sc.nextInt(); // 건물 총 층수
        int S = sc.nextInt(); // 현재 층
        int G = sc.nextInt(); // 이동 목표 층
        int U = sc.nextInt(); // 1회 위로 이동 가능한 층 수
        int D = sc.nextInt(); // 1회 아래로 이동 가능한 층 수

        boolean[] visited = new boolean[F + 1];
        int minTouch = Integer.MAX_VALUE;
        Queue<Elevator> queue = new LinkedList<>();
        int[] moves = {U, -D};

        queue.add(new Elevator(S, 0));
        visited[S] = true;

        while (!queue.isEmpty()) {
            Elevator e = queue.poll();

            if (e.floor == G) {
                minTouch = Math.min(minTouch, e.moveCount);
                continue;
            }

            for (int move : moves) {
                int nf = e.floor + move;

                if (1 <= nf && nf <= F && !visited[nf]) {
                    queue.add(new Elevator(nf, e.moveCount + 1));
                    visited[nf] = true;
                }
            }
        }

        if (minTouch == Integer.MAX_VALUE) {
            System.out.println("use the stairs");
        } else {
            System.out.println(minTouch);
        }
    }

    static class Elevator {
        int floor;
        int moveCount;

        public Elevator(int floor, int moveCount) {
            this.floor = floor;
            this.moveCount = moveCount;
        }
    }
}
