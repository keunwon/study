package com.myshop.order.command.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Canceller {
    private final String memberId;

    public static Canceller of(String memberId) {
        return new Canceller(memberId);
    }
}
