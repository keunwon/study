package com.myshop.member.command.domain.model;

import lombok.Getter;

@Getter
public final class Password {
    private final String value;

    public Password(String value) {
        this.value = value;
    }

    public boolean match(String password) {
        return this.value.equals(password);
    }
}
