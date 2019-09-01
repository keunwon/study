package com.keunwon.java8.chap07;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStreams {

    public static void main(String ... args) {
        System.out.println(sequentialSum(10));
        System.out.println(iterativeSum(10));
    }

    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                     .limit(n)
                     .reduce(0L, Long::sum);
    }

    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 0; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public static long paralleSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                     .limit(n)
                     .parallel()
                     .reduce(0L, Long::sum);
    }

    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                         .reduce(0L, Long::sum);
    }

    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                         .parallel()
                         .reduce(0L, Long::sum);
    }
}
