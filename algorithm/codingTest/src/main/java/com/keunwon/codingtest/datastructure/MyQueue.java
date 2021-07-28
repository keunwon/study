package com.keunwon.codingtest.datastructure;

import java.util.ArrayList;
import java.util.List;

public class MyQueue<T> {
    private final List<T> queue = new ArrayList<>();

    public void enqueue(T item) {
        queue.add(item);
    }

    public T dequeue() {
        if (queue.isEmpty()) { return null; }

        return queue.remove(0);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
