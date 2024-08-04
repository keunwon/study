package com.keunwon.algorithm.programmers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Lesson17682Test {

    @ParameterizedTest
    @MethodSource("base")
    void case_1(String dartResult, int result) {
        var actual = new Lesson17682().solution(dartResult);
        assertThat(actual).isEqualTo(result);
    }

    private static Stream<Arguments> base() {
        return Stream.of(
                Arguments.of("1S2D*3T", 37),
                Arguments.of("1D2S#10S", 9),
                Arguments.of("1D2S0T", 3),
                Arguments.of("1S*2T*3S", 23),
                Arguments.of("1D#2S*3S", 5),
                Arguments.of("1T2D3D#", -4),
                Arguments.of("1D2S3T*", 59),
                Arguments.of("10S1S0S*", 12)
        );
    }
}
