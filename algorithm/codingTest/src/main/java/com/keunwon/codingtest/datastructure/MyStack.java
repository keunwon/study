package com.keunwon.codingtest.datastructure;

import java.util.ArrayList;
import java.util.List;

public class MyStack<T> {
    private final List<T> statck = new ArrayList<>();

    public void push(T item) {
        statck.add(item);
    }

    public T pop() {
        if (statck.isEmpty()) { return null; }

        return statck.remove(statck.size() - 1);
    }

    public boolean isEmpty() {
        return statck.isEmpty();
    }
}
