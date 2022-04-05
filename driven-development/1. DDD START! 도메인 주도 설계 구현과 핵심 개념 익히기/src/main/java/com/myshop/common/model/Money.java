package com.myshop.common.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
@Getter
@AllArgsConstructor
@ToString(of = "value")
@EqualsAndHashCode(of = "value")
public final class Money {
    private int value;

    public Money multiply(int multiplier) {
        return new Money(value * multiplier);
    }
}
