package com.keunwon.codingtest.chapter2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class FactorialTest {

    @Test
    @DisplayName("팩토리")
    public void factory() {
        // given
        final int value = 4;

        // then
        final int result = new Factorial().factorialFunc(5);

        // then
        assertThat(result).isEqualTo(120);
    }

    @Test
    @DisplayName("리스트의 모든 값 저장")
    public void sumList() {
        // given
        final List<Integer> list = Arrays.asList(1, 2, 3, 4);

        // when
        final int sum = new Factorial().sum(list);

        // then
        assertThat(sum).isEqualTo(10);
    }

    @Test
    @DisplayName("재귀함수-연습문제")
    public void recursiveQuestion() {
        // given
        final int value = 6;

        // when
        final int result = new Factorial().recursive(6);

        // then
        assertThat(result).isEqualTo(24);
    }

    @Test
    @DisplayName("피보나치 알고리즘")
    public void fibonacci() {
        // given
        Factorial factorial = new Factorial();

        // when, then
        assertThat(factorial.fibonacci(4)).isEqualTo(3);
        assertThat(factorial.fibonacci(5)).isEqualTo(5);
        assertThat(factorial.fibonacci(8)).isEqualTo(21);
        assertThat(factorial.fibonacci(9)).isEqualTo(34);
    }
}