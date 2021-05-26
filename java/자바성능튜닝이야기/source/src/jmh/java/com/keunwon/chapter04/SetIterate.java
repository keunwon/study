package com.keunwon.chapter04;

import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode({ Mode.AverageTime })
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class SetIterate {
    private final int LOOP_COUNT = 1000;
    private final String data = "abcdefghijklmnopqrstuvwxyz";

    private Set<String> hashSet;
    private Set<String> treeSet;
    private Set<String> linkedHashSet;
    private String[] keys;

    String result = null;

    @Setup(Level.Trial)
    public void setUp() {
        hashSet = new HashSet<>();
        treeSet = new TreeSet<>();
        linkedHashSet = new LinkedHashSet<>();

        for (int loop = 0; loop < LOOP_COUNT; loop++) {
            String tempData = data + loop;
            hashSet.add(tempData);
            treeSet.add(tempData);
            linkedHashSet.add(tempData);
        }
    }

    @Benchmark
    public void iterateHashSet() {
        Iterator<String> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
    }

    @Benchmark
    public void iterateTreeSet() {
        Iterator<String> iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
    }

    @Benchmark
    public void iterateLinkedHashSet() {
        Iterator<String> iterator = linkedHashSet.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
    }
}