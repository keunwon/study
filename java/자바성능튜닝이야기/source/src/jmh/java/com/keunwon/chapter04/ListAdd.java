package com.keunwon.chapter04;

import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.TimeUnit;


@State(Scope.Thread)
@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class ListAdd {
    private final int LOOP_COUNT = 1000;

    private List<Integer> arrayList;
    private List<Integer> vector;
    private LinkedList<Integer> linkedList;

    @Benchmark
    public void addArrayList() {
        arrayList = new ArrayList<>();
        for (int loop = 0; loop < LOOP_COUNT; loop++) {
            arrayList.add(loop);
        }
    }

    @Benchmark
    public void addVector() {
        vector = new Vector<>();
        for (int loop = 0; loop < LOOP_COUNT; loop++) {
            vector.add(loop);
        }
    }

    @Benchmark
    public void addLinkedList() {
        linkedList = new LinkedList<>();
        for (int loop = 0; loop < LOOP_COUNT; loop++) {
            linkedList.add(loop);
        }
    }
}
