package com.keunwon.chapter04;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class ListRemove {
    private final int LOOP_COUNT = 10;

    private List<Integer> arrayList;
    private List<Integer> vector;
    private LinkedList<Integer> linkedList;

    @Setup(Level.Trial)
    public void setUp() {
        arrayList = new LinkedList<>();
        vector = new Vector<>();
        linkedList = new LinkedList<>();

        for (int loop = 0; loop < LOOP_COUNT; loop++) {
            arrayList.add(loop);
            vector.add(loop);
            linkedList.add(loop);
        }
    }

    @Benchmark
    public void removeArrayListFromFirst() {
        ArrayList<Integer> tempList = new ArrayList<>(arrayList);
        for (int loop = 0; loop < LOOP_COUNT; loop++) {
            tempList.remove(0);
        }
    }

    @Benchmark
    public void removeVectorFromFirst() {
        List<Integer> tempList = new Vector<>(vector);
        for (int loop = 0; loop < LOOP_COUNT; loop++) {
            tempList.remove(0);
        }
    }

    @Benchmark
    public void removeLinkedListFromFirst() {
        LinkedList<Integer> tempList = new LinkedList<>(linkedList);
        for (int loop = 0; loop < LOOP_COUNT; loop++) {
            tempList.remove(0);
        }
    }

    @Benchmark
    public void removeArrayListFromLast() {
        ArrayList<Integer> tempList = new ArrayList<>(arrayList);
        for (int loop = LOOP_COUNT -1; loop >= 0; loop--) {
            tempList.remove(loop);
        }
    }

    @Benchmark
    public void removeVectorFromLast() {
        List<Integer> tempList = new Vector<>(vector);
        for (int loop = LOOP_COUNT -1; loop >= 0 ; loop--) {
            tempList.remove(loop);
        }
    }

    @Benchmark
    public void removeLinkedListFromLast() {
        LinkedList<Integer> tempList = new LinkedList<>(linkedList);
        for (int loop = LOOP_COUNT - 1; loop >= 0 ; loop--) {
            tempList.remove(loop);
        }
    }
}
