package com.myshop.member.command.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public final class ChangePasswordRequest {
    private String memberId;
    private String oldPassword;
    private String newPassword;
}
