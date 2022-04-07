package com.myshop.member.domain;

import lombok.val;

public class Password {
    private String value;

    public Password(String value) {
        this.value = value;
    }

    public boolean match(String password) {
        return this.value.equals(password);
    }
}
