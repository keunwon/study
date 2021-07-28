package com.keunwon.codingtest.datastructure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class SingleLinkedListTest {

    @Test
    @DisplayName("링크드리스트 헤더 값 검증")
    public void case_01() {
        // given
        SingleLinkedList<Integer> linkedList = new SingleLinkedList<>();
        linkedList.addNode(1);

        // when
        Integer result = linkedList.head.data;

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("리스트 중간에 값 추가")
    public void case_02() {
        // given
        SingleLinkedList<Integer> linkedList = new SingleLinkedList<>();
        linkedList.addNode(1);
        linkedList.addNode(3);

        // when
        linkedList.addNode(1, 2);
        List<Integer> result = linkedList.allData();

        // then
        System.out.println(result);
    }

    @Test
    @DisplayName("데이터 삭제")
    public void case_03() {
        // given
        SingleLinkedList<Integer> linkedList = new SingleLinkedList<>();
        linkedList.addNode(1);
        linkedList.addNode(2);
        linkedList.addNode(3);
        linkedList.addNode(4);
        linkedList.addNode(5);

        // when
        linkedList.remove(1);
        linkedList.remove(3);
        linkedList.remove(5);
        List<Integer> result = linkedList.allData();

        // then
        assertThat(result).isEqualTo(Arrays.asList(2, 4));
    }
}