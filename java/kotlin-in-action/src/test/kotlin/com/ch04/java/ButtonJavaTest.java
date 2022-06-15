package com.ch04.java;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.NotSerializableException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ButtonJavaTest {

    @DisplayName("직렬화시 NotSerializableException 발생합니다")
    @Test
    void serialize() {
        var buttonJava = new ButtonJava();

        assertThatExceptionOfType(NotSerializableException.class)
                .isThrownBy(buttonJava::serialize);
    }
}