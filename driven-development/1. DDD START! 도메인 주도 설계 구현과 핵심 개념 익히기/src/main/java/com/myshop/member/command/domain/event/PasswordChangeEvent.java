package com.myshop.member.command.domain.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PasswordChangeEvent {
    private final String id;
    private final String newPassword;
}
