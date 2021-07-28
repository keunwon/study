package com.keunwon.codingtest.datastructure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class DoubleLinkedListTest {

    @Test
    @DisplayName("데이터 추가")
    public void case_01() {
        // given
        DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();
        linkedList.addNode(1);
        linkedList.addNode(2);
        linkedList.addNode(3);
        linkedList.addNode(4);

        // when
        List<Integer> result = linkedList.allData();

        // then
        assertThat(result).isEqualTo(Arrays.asList(1, 2, 3, 4));
    }

    @Test
    @DisplayName("데이터 찾기")
    public void case_02() {
        // given
        DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();
        linkedList.addNode(1);
        linkedList.addNode(2);
        linkedList.addNode(3);
        linkedList.addNode(4);

        // when
        Integer findHead1 = linkedList.searchFromHead(3);
        Integer findHead2 = linkedList.searchFromHead(1);
        Integer findHead3 = linkedList.searchFromHead(4);
        Integer findHead4 = linkedList.searchFromHead(7);

        Integer findTail1 = linkedList.searchFromTail(3);
        Integer findTail2 = linkedList.searchFromTail(1);
        Integer findTail3 = linkedList.searchFromTail(4);
        Integer findTail4 = linkedList.searchFromTail(7);

        // then
        assertThat(findHead1).isEqualTo(3);
        assertThat(findHead2).isEqualTo(1);
        assertThat(findHead3).isEqualTo(4);
        assertThat(findHead4).isNull();


        assertThat(findTail1).isEqualTo(3);
        assertThat(findTail2).isEqualTo(1);
        assertThat(findTail3).isEqualTo(4);
        assertThat(findTail4).isNull();
    }
}