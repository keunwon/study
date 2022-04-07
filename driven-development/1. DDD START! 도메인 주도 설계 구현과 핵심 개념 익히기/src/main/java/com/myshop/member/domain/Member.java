package com.myshop.member.domain;

public class Member {
    private Password password;

    public void changePassword(String currentPassword, String newPassword) {
        if (!password.match(newPassword)) {
            throw new PasswordNotMatchException();
        }
        this.password = new Password(newPassword);
    }
}
