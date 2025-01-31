package com.keunwon.java8.chap03;

public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}
