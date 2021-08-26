package com.keunwon.algorithm.datastructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Problem10866 {
    static Deque<Integer> deque = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            String[] input = br.readLine().split(" ");

            switch (input[0]) {
                case "push_front":
                    pushFront(Integer.parseInt(input[1]));
                    break;
                case "push_back":
                    pushBack(Integer.parseInt(input[1]));
                    break;
                case "pop_front":
                    System.out.println(popFront());
                    break;
                case "pop_back":
                    System.out.println(popBack());
                    break;
                case "size":
                    System.out.println(size());
                    break;
                case "empty":
                    System.out.println(empty());
                    break;
                case "front":
                    System.out.println(front());
                    break;
                case "back":
                    System.out.println(back());
                    break;
            }
        }
    }

    static void pushFront(int num) {
        deque.addFirst(num);;
    }

    static void pushBack(int num){
        deque.addLast(num);
    }

    static int popFront() {
        return deque.isEmpty() ? -1 :deque.removeFirst();
    }

    static int popBack() {
        return deque.isEmpty() ? -1 : deque.removeLast();
    }

    static int size() {
        return deque.size();
    }

    static int empty() {
        return deque.isEmpty() ? 1 : 0;
    }

    static int front() {
        return deque.isEmpty() ? -1 : deque.peekFirst();
    }

    static int back() {
        return deque.isEmpty() ? -1 : deque.peekLast();
    }
}
