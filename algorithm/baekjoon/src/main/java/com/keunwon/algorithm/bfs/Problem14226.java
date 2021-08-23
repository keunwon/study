package com.keunwon.algorithm.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem14226 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        Queue<Emoticon> queue = new LinkedList<>();
        boolean[][] visited = new boolean[1001][1001];

        queue.add(new Emoticon(1, 0, 0));
        visited[1][0] = true;

        while (!queue.isEmpty()) {
            Emoticon e = queue.poll();

            if (e.num == N) {
                System.out.println(e.second);
                break;
            }

            // 복사
            queue.offer(new Emoticon(e.num, e.num, e.second + 1));

            // 붙여넣기
            int paste = e.num + e.copyNum;
            if (e.copyNum != 0 && paste <= 1000 && !visited[paste][e.copyNum]) {
                queue.add(new Emoticon(paste, e.copyNum, e.second + 1));
                visited[paste][e.copyNum] = true;
            }

            // 삭제
            int del = e.num - 1;
            if (1 <= e.num && !visited[del][e.copyNum]) {
                queue.add(new Emoticon(del, e.copyNum, e.second + 1));
                visited[del][e.copyNum] = true;
            }
        }
    }

    static class Emoticon {
        int num;
        int copyNum;
        int second;

        public Emoticon(int num, int copyNum, int second) {
            this.num = num;
            this.copyNum = copyNum;
            this.second = second;
        }
    }
}
