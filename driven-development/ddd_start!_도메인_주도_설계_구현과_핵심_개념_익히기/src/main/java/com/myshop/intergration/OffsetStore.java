package com.myshop.intergration;

public interface OffsetStore {
    long get();
    void update(long nextOffset);
}
