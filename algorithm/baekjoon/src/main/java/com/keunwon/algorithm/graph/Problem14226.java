package com.keunwon.algorithm.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem14226 {
    private static int S;
    private static boolean[][] visited;

    public static void main(String[] args) {
        input();
        bfs();
    }

    private static void bfs() {
        Queue<Emoji> queue = new LinkedList<>();

        queue.offer(new Emoji(1, 0, 0));
        visited[1][0] = true;

        while (!queue.isEmpty()) {
            Emoji e = queue.poll();

            if (e.num == S) {
                System.out.println(e.seconds);
                break;
            }

            // copy
            queue.offer(new Emoji(e.num, e.num, e.seconds + 1));

            // paste
            final int paste = e.num + e.copyNum;
            if (e.copyNum != 0 && paste <= 1000 && !visited[paste][e.copyNum]) {
                queue.offer(new Emoji(paste, e.copyNum, e.seconds + 1));
                visited[paste][e.copyNum] = true;
            }

            // delete
            final int delete = e.num - 1;
            if (1 <= e.num && !visited[delete][e.copyNum]) {
                queue.offer(new Emoji(delete, e.copyNum, e.seconds + 1));
                visited[delete][e.copyNum] = true;
            }
        }
    }

    private static void input() {
        Scanner sc = new Scanner(System.in);
        S = sc.nextInt();
        visited = new boolean[1001][1001];
    }

    private static class Emoji {
        int num;
        int copyNum;
        int seconds;

        Emoji(int num, int copyNum, int seconds) {
            this.num = num;
            this.copyNum = copyNum;
            this.seconds = seconds;
        }
    }
}
