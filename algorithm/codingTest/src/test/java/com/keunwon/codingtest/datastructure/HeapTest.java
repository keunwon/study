package com.keunwon.codingtest.datastructure;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class HeapTest {

    @Test
    public void case_01() {
        // given
        Heap heap = new Heap(15);
        heap.insert(10);
        heap.insert(8);
        heap.insert(5);
        heap.insert(4);
        heap.insert(20);

        // when, then
        assertThat(heap.heapArray.get(1)).isEqualTo(20);
    }
}