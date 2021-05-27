package com.keunwon.chapter05;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class ForLoop {
    private final int LOOP_COUNT = 100000;

    private List<Integer> list;

    @Setup
    public void setUp() {
        list = new ArrayList<>(LOOP_COUNT);
        for (int loop = 0; loop < LOOP_COUNT; loop++) {
            list.add(loop);
        }
    }

    @Benchmark
    public void traditionalForLoop() {
        int listSize = list.size();
        for (int loop = 0; loop < listSize; loop++) {
            resultProcess(list.get(loop));
        }
    }

    @Benchmark
    public void traditionalSizeForLoop() {
        for (int loop = 0; loop < list.size(); loop++) {
            resultProcess(list.get(loop));
        }
    }

    @Benchmark
    public void timeForEachLoop() {
        for (Integer i : list) {
            resultProcess(i);
        }
    }

    int currnet;
    public void resultProcess(int result) {
        currnet = result;
    }
}
