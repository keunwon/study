package com.keunwon.algorithm.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem13549 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        Queue<Position> queue = new LinkedList<>();
        boolean[] visited = new boolean[100001];
        int min = 100000;

        queue.add(new Position(N, 0));

        while (!queue.isEmpty()) {
            Position p = queue.poll();
            visited[p.x] = true;

            if (p.x == K) {
                min = Math.min(min, p.second);
            }

            int[] seconds = {0, 1, 1};
            int[] moves = { p.multiple(), p.plus(), p.minus() };

            for (int i = 0; i < moves.length; i++) {
                final int move = moves[i];
                final int second = seconds[i];

                if (0 <= move && move <= 100000 && !visited[move]) {
                    queue.add(new Position(move, p.second + second));
                }
            }

        }

        System.out.println(min);
    }

    static class Position {
        int x;
        int second;

        public Position(int x, int second) {
            this.x = x;
            this.second = second;
        }

        int plus() {
            return x + 1;
        }

        int minus() {
            return x - 1;
        }

        int multiple() {
            return x * 2;
        }
    }
}
