package com.myshop.member.command.domain.event;

import com.myshop.common.event.Events;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberUnblockedEvent extends Events {
    private final String memberId;
}
