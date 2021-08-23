package com.keunwon.algorithm.bfs;

import java.util.*;

public class Problem12851 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        boolean[] visited = new boolean[100001];
        Queue<Person> queue = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int minSecond = Integer.MAX_VALUE;

        queue.add(new Person(N, 0));


        while (!queue.isEmpty()) {
            Person p = queue.poll();
            visited[p.x] = true;

            if (p.x == K) {
                if (!map.containsKey(p.second)) {
                    map.put(p.second, 1);
                } else {
                    map.put(p.second, map.get(p.second) + 1);
                }

                minSecond = Math.min(minSecond, p.second);
                continue;
            }

            int[] moves = {p.multiple(), p.plus(), p.minus()};
            for (int move : moves) {
                if (0 <= move && move <= 100000 && !visited[move]) {
                    queue.add(new Person(move, p.second + 1));
                }
            }
        }

        System.out.println(minSecond);
        System.out.println(map.get(minSecond));
    }

    static class Person {
        int x;
        int second;

        public Person(int x, int second) {
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
