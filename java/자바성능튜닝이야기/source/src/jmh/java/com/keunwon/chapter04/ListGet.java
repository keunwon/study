package com.keunwon.chapter04;

import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode({Mode.AverageTime })
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class ListGet {
    private final int LOOP_COUNT = 1000;

    private List<Integer> arrayList;
    private List<Integer> vector;
    private LinkedList<Integer> linkedList;
    private Queue<Integer> queue;

    int result = 0;

    @Setup
    public void setUp() {
        arrayList = new ArrayList<>();
        vector = new Vector<>();
        linkedList = new LinkedList<>();
        queue = new LinkedList<>();

        for (int loop = 0; loop < LOOP_COUNT; loop++) {
            arrayList.add(loop);
            vector.add(loop);
            linkedList.add(loop);
            queue.add(loop);
        }
    }

    @Benchmark
    public void getArrayList() {
        for (int loop = 0; loop < LOOP_COUNT; loop++) {
            result = arrayList.get(loop);
        }
    }

    @Benchmark
    public void getVector() {
        for (int loop = 0; loop < LOOP_COUNT; loop++) {
            result = vector.get(loop);
        }
    }

    @Benchmark
    public void getLinkedList() {
        for (int loop = 0; loop < LOOP_COUNT; loop++) {
            result = linkedList.get(loop);
        }
    }

    @Benchmark
    public void getQueue() {
        for (int loop = 0; loop < LOOP_COUNT; loop++) {
            if (queue.peek() == null) { break; }
            result = queue.poll();
        }
    }
}
