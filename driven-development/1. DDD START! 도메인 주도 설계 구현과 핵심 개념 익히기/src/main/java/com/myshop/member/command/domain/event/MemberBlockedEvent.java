package com.myshop.member.command.domain.event;

import com.myshop.common.event.Event;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberBlockedEvent extends Event {
    private final String memberId;
}
