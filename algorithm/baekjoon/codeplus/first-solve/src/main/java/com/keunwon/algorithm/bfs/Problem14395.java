package com.keunwon.algorithm.bfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Problem14395 {

    private static long s, t;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        s = sc.nextLong();
        t = sc.nextLong();

        if (s == t) {
            System.out.println(0);
            return;
        } else if (t == 1) {
            System.out.println("/");
            return;
        }

        bfs();
    }

    private static void bfs() {
        var queue = new LinkedList<Node>();
        var visited = new HashSet<Long>();

        queue.offer(new Node(s, new ArrayList<>()));
        visited.add(s);

        while (!queue.isEmpty()) {
            var node = queue.poll();

            if (node.num == t) {
                node.operations.forEach(System.out::print);
                return;
            }

            for (int i = 0; i < 4; i++) {
                var operations = new ArrayList<>(node.operations);
                var num = 0L;

                if (i == 0) {
                    num = node.num * node.num;
                    operations.add("*");
                } else if (i == 1) {
                    num = node.num + node.num;
                    operations.add("+");
                } else if (i == 2) {
                    operations.add("-");
                } else {
                    num = 1;
                    operations.add("/");
                }

                if (t < num || visited.contains(num)) {
                    continue;
                }

                visited.add(num);
                queue.offer(new Node(num, operations));
            }
        }

        System.out.println(-1);
    }

    private static class Node {
        final long num;
        final List<String> operations;

        Node(long num, List<String> operations) {
            this.num = num;
            this.operations = operations;
        }
    }
}
