package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem9019 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T -- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            boolean[] visited = new boolean[100001];
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            bfs(A, B, visited);
        }
    }

    static void bfs(int a, int b, boolean[] visited) {
        Queue<Register> queue = new LinkedList<>();

        queue.add(new Register(a, ""));
        visited[a] = true;

        while (!queue.isEmpty()) {
            Register r = queue.poll();

            if (r.num == b) {
                System.out.println(r.command);
                break;
            }

            String[] command = {"D", "S", "L", "R"};
            int[] commandValues = {r.runD(), r.runS(), r.runL(), r.runR()};

            for (int i = 0; i < command.length; i++) {
                if (!visited[commandValues[i]]) {
                    queue.add(new Register(commandValues[i], r.command + command[i]));
                    visited[commandValues[i]] = true;
                }
            }
        }
    }

    static class Register {
        int num;
        String command;

        public Register(int num, String command) {
            this.num = num;
            this.command = command;
        }

        int runD() {
            return (num * 2) % 10000;
        }

        int runS() {
            return num == 0 ? 9999 : num - 1;
        }

        int runL() {
            return num % 1000 * 10 + num / 1000;
        }

        int runR() {
            return num % 10 * 1000 + num / 10;
        }
    }
}
