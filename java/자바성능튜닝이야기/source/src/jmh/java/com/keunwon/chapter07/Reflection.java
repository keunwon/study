package com.keunwon.chapter07;

import org.openjdk.jmh.annotations.*;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class Reflection {
    private final int LOOP_COUNT = 100;

    private String result;

    @Benchmark
    public void withEquals() {
        Object src = new BigDecimal("6");
        for (int loop = 0; loop < LOOP_COUNT; loop++) {
            if (src.getClass().getName().equals("java.math.BigDecimal")) {
                result = "BigDecimal";
            }
        }
    }

    @Benchmark
    public void withInstanceOf() {
        Object src = new BigDecimal("6");
        for (int loop = 0; loop < LOOP_COUNT; loop++) {
            if (src instanceof BigDecimal) {
                result = "BigDecimal";
            }
        }
    }
}
